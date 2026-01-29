#!/usr/bin/env python3
"""
Script para análisis exploratorio de datos
"""
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

def exploratory_analysis(data_file):
    """Realiza análisis exploratorio de datos"""
    
    print("Realizando análisis exploratorio...")
    df = pd.read_csv(data_file)
    
    # Configurar estilo de gráficos
    plt.style.use('seaborn-v0_8-darkgrid')
    
    # 1. Distribución por sector
    plt.figure(figsize=(10, 6))
    sector_counts = df['Sector de actividad'].value_counts()
    plt.pie(sector_counts.values, labels=sector_counts.index, autopct='%1.1f%%')
    plt.title('Distribución por Sector de Actividad')
    plt.savefig('../docs/sector_distribution.png', dpi=300, bbox_inches='tight')
    
    # 2. Ahorro por comunidad (top 10)
    plt.figure(figsize=(12, 6))
    ahorro_comunidad = df.groupby('CCAA')['Total ahorro solicitado (kWh)'].sum().nlargest(10)
    ahorro_comunidad.plot(kind='bar')
    plt.title('Top 10 Comunidades por Ahorro Total (kWh)')
    plt.xlabel('Comunidad Autónoma')
    plt.ylabel('Ahorro Total (kWh)')
    plt.xticks(rotation=45)
    plt.tight_layout()
    plt.savefig('../docs/top_comunidades.png', dpi=300, bbox_inches='tight')
    
    # 3. Distribución por estado
    plt.figure(figsize=(8, 6))
    estado_counts = df[';'].value_counts()
    sns.barplot(x=estado_counts.index, y=estado_counts.values)
    plt.title('Distribución por Estado de Solicitud')
    plt.xlabel('Estado')
    plt.ylabel('Número de Solicitudes')
    plt.tight_layout()
    plt.savefig('../docs/estado_distribution.png', dpi=300, bbox_inches='tight')
    
    # 4. Correlaciones
    numeric_cols = df.select_dtypes(include=['float64', 'int64']).columns
    if len(numeric_cols) > 1:
        plt.figure(figsize=(10, 8))
        correlation_matrix = df[numeric_cols].corr()
        sns.heatmap(correlation_matrix, annot=True, cmap='coolwarm', center=0)
        plt.title('Matriz de Correlación')
        plt.tight_layout()
        plt.savefig('../docs/correlation_matrix.png', dpi=300, bbox_inches='tight')
    
    # 5. Estadísticas resumen
    print("\n=== RESUMEN ESTADÍSTICO ===")
    print(f"Total de registros: {len(df)}")
    print(f"Total de solicitudes únicas: {df['Id_solicitud'].nunique()}")
    print(f"Ahorro total solicitado: {df['Total ahorro solicitado (kWh)'].sum():,.2f} kWh")
    print(f"Ahorro promedio por solicitud: {df['Total ahorro solicitado (kWh)'].mean():,.2f} kWh")
    print(f"Actuaciones promedio por solicitud: {df['Número de actuaciones'].mean():.2f}")
    
    print("\nDistribución por sector:")
    print(sector_counts)
    
    print("\nDistribución por estado:")
    print(estado_counts)
    
    plt.show()

if __name__ == "__main__":
    data_file = "../data/SGC_clean.csv"
    
    try:
        exploratory_analysis(data_file)
    except FileNotFoundError:
        print(f"Error: No se encontró el archivo {data_file}")
        print("Ejecuta primero import_data.py")