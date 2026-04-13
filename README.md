import os
import re
import nltk
import streamlit as st
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize

# Download NLTK data
nltk.download('punkt')
nltk.download('stopwords')

# -------------------------------
# 🔹 Text Preprocessing Function
# -------------------------------
def preprocess_text(text):
    text = text.lower()
    text = re.sub(r'[^a-zA-Z ]', '', text)
    tokens = word_tokenize(text)
    tokens = [word for word in tokens if word not in stopwords.words('english')]
    return " ".join(tokens)

# -------------------------------
# 🔹 Get TF-IDF Vectors
# -------------------------------
def get_vectors(job_desc, resumes):
    documents = [job_desc] + resumes
    vectorizer = TfidfVectorizer()
    vectors = vectorizer.fit_transform(documents)
    return vectors

# -------------------------------
# 🔹 Compute Similarity
# -------------------------------
def compute_similarity(vectors):
    job_vector = vectors[0]
    resume_vectors = vectors[1:]
    similarities = cosine_similarity(job_vector, resume_vectors)
    return similarities[0]

# -------------------------------
# 🔹 Streamlit UI
# -------------------------------
st.set_page_config(page_title="Resume Screening AI", layout="centered")

st.title("📄 Resume Screening AI System")

st.write("Upload resumes and compare with job description using NLP")

# Job Description Input
job_desc = st.text_area("📌 Enter Job Description")

# Upload Resumes
uploaded_files = st.file_uploader("📂 Upload Resume Files (.txt only)", accept_multiple_files=True)

# Button
if st.button("🔍 Analyze Resumes"):

    if not job_desc:
        st.warning("⚠️ Please enter job description")
    
    elif not uploaded_files:
        st.warning("⚠️ Please upload resumes")
    
    else:
        resumes = []
        names = []

        for file in uploaded_files:
            content = file.read().decode("utf-8")
            resumes.append(preprocess_text(content))
            names.append(file.name)

        job_clean = preprocess_text(job_desc)

        # Vectorization
        vectors = get_vectors(job_clean, resumes)

        # Similarity
        scores = compute_similarity(vectors)

        # Ranking
        results = list(zip(names, scores))
        results.sort(key=lambda x: x[1], reverse=True)

        # Display Results
        st.success("✅ Analysis Complete")

        st.subheader("🏆 Ranked Candidates")
        for i, (name, score) in enumerate(results, 1):
            st.write(f"{i}. {name} → Score: {score:.2f}")
