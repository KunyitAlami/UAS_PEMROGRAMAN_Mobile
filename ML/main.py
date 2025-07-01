from fastapi import FastAPI
from pydantic import BaseModel
import numpy as np
from tensorflow.keras.models import load_model
import joblib

app = FastAPI()

model = load_model("diabetes_mlp_model.keras")
scaler = joblib.load("scaler.pkl")

# urutan kolom saat training dataset
FEATURE_ORDER = ["Pregnancies", "Glucose", "BloodPressure", "SkinThickness",
                 "Insulin", "BMI", "DiabetesPedigreeFunction", "Age"]

# urutan skema inputan data dari aplikasi mobile nanti dan tipe datanya
class InputData(BaseModel):
    Pregnancies: float
    Glucose: float
    BloodPressure: float
    SkinThickness: float
    Insulin: float
    BMI: float
    DiabetesPedigreeFunction: float
    Age: float

# endpoint yang akan dihit saat di fastapi nanti
@app.post("/predict")
def predict(data: InputData):
    input_dict = data.dict()
    values = [input_dict[feature] for feature in FEATURE_ORDER]

    # melakukan scalling dengan scaller yang sama saat dilatihan 
    input_scaled = scaler.transform([values])

    # melakukan prediksi dengan model mlp dari hasil latihan 
    prediction = model.predict(input_scaled)[0][0]
    result = int(prediction > 0.5)

    result_text = "Berisiko terkena diabetes" if result == 1 else "Tidak berisiko terkena diabetes"

    return {
        "probability": float(prediction),
        "message": result_text
    }
