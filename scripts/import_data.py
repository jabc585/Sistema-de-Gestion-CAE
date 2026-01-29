#!/usr/bin/env python3
"""
Script para importar y preprocesar datos del CSV
"""
import pandas as pd
import numpy as np
import os

def import_and_clean_data(input_file, output_file):
    """Importa y limpia datos del CSV"""
    
    print(f"Importando datos de {input_file}...")
    
    # Leer CSV con delimitador punto y coma
    df = pd.read_csv(input_file, delimiter=';', encoding='utf-8')
    
    print(f"Datos originales: {df.shape[0]} filas, {df.shape[1]} columnas")
    
    # Limpiar nombres de columnas
    df.columns = df.columns.str.strip()
    
    # Convertir columnas numéricas
    numeric_columns = ['Total ahorro solicitado (kWh)', 'Número de actuaciones', 'Ahorro de la actuación(kWh)']
    
    for col in numeric_columns:
        if col in df.columns:
            df[col] = pd.to_numeric(df[col].astype(str).str.replace('.', '').str.replace(',', '.'), errors='coerce')
    
    # Eliminar duplicados
    df = df.drop_duplicates()
    
    # Guardar datos limpios
    df.to_csv(output_file, index=False, encoding='utf-8')
    print(f"Datos limpios guardados en {output_file}")
    print(f"Datos finales: {df.shape[0]} filas, {df.shape[1]} columnas")
    
    # Mostrar estadísticas básicas
    print("\nEstadísticas básicas:")
    print(f"Ahorro total: {df['Total ahorro solicitado (kWh)'].sum():,.2f} kWh")
    print(f"Ahorro promedio: {df['Total ahorro solicitado (kWh)'].mean():,.2f} kWh")
    print(f"Total solicitudes: {df['Id_solicitud'].nunique()}")
    
    return df

if __name__ == "__main__":
    input_file = "../data/SGC.csv"
    output_file = "../data/SGC_clean.csv"
    
    if os.path.exists(input_file):
        df = import_and_clean_data(input_file, output_file)
    else:
        print(f"Error: No se encontró el archivo {input_file}")