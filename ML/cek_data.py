import pandas as pd
import numpy as np

df = pd.read_csv("D:/GHANI/Semester 4/Kuliah/Pemro Mobile I dan Praktikum/UAS Mobile/diabetes.csv")

kolom_selain_outcome = df.columns.drop("Outcome")
kolom_dengan_nilai_nol = [col for col in kolom_selain_outcome if (df[col] == 0).any()]
baris_nol = df[(df[kolom_dengan_nilai_nol] == 0).any(axis=1)]

print("\nBaris-baris dengan nilai 0 di salah satu kolom:")
print(baris_nol)

print(f"\nJumlah total baris dengan nilai 0: {len(baris_nol)}")
print("Kolom yang mengandung nilai 0:", kolom_dengan_nilai_nol)

jumlah_tidak_diabetes = (df["Outcome"] == 0).sum()
jumlah_diabetes = (df["Outcome"] == 1).sum()

print("\nJumlah orang yang tidak terkena diabetes (Outcome = 0):", jumlah_tidak_diabetes)
print("Jumlah orang yang terkena diabetes (Outcome = 1):", jumlah_diabetes)

total = jumlah_diabetes + jumlah_tidak_diabetes
print(f"\nDistribusi kelas:")
print(f"  - Tidak diabetes: {jumlah_tidak_diabetes/total:.2%}")
print(f"  - Diabetes: {jumlah_diabetes/total:.2%}")

