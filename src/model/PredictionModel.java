package model;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.Evaluation;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class PredictionModel {
    private RandomForest randomForest;
    private Instances dataset;
    private ArrayList<Attribute> attributes;

    public PredictionModel() {
        this.randomForest = new RandomForest();
        this.randomForest.setNumIterations(100);
    }

    public void trainModel(List<Solicitud> solicitudes) throws Exception {
        // Definir atributos
        attributes = new ArrayList<>();
        
        // Atributos categóricos
        ArrayList<String> sectores = new ArrayList<>();
        sectores.add("Industrial");
        sectores.add("Terciario");
        sectores.add("Residencial");
        sectores.add("Agropecuario");
        sectores.add("Transporte");
        attributes.add(new Attribute("sector", sectores));  

        ArrayList <String> estados = new ArrayList<>();
        estados.add("Favorable");
        estados.add("Desfavorable");
        estados.add("Desistimiento");
        attributes.add(new Attribute("estado", estados));

        // Atributos numéricos
        attributes.add(new Attribute("añoFinEjecucion"));
        attributes.add(new Attribute("numeroActuaciones"));

        //Clase (ahorro total)
        attributes.add(new Attribute("ahorroTotal"));

        // Crear dataset 
        dataset = new Instances("SGC", attributes, 0);
        dataset.setClassIndex(attributes.size() - 1);

  // Agregar instancias
        for (Solicitud s : solicitudes) {
            double[] values = new double[dataset.numAttributes()];
            
            // Sector
            values[0] = sectores.indexOf(s.getSector());
            
            // Estado
            values[1] = estados.indexOf(s.getEstado());
            
            // Año
            values[2] = s.getAñoFinEjecucion();
            
            // Número actuaciones
            values[3] = s.getNumeroActuaciones();
            
            // Ahorro total (clase)
            values[4] = s.getTotalAhorroSolicitado();
            
            dataset.add(new DenseInstance(1.0, values));
        }
        
        // Entrenar modelo
        randomForest.buildClassifier(dataset);
        
        // Evaluar modelo
        Evaluation eval = new Evaluation(dataset);
        eval.crossValidateModel(randomForest, dataset, 10, new Random(1));
        
        System.out.println("Modelo entrenado exitosamente:");
        System.out.println("Precisión: " + eval.pctCorrect() + "%");
        System.out.println("MAE: " + eval.meanAbsoluteError());
    }
    
    public double predictAhorro(String sector, String estado, int año, int numActuaciones) throws Exception {
        double[] values = new double[attributes.size() - 1];
        
        ArrayList<String> sectores = (ArrayList<String>) attributes.get(0).enumerateValues();
        ArrayList<String> estados = (ArrayList<String>) attributes.get(1).enumerateValues();
        
        values[0] = sectores.indexOf(sector);
        values[1] = estados.indexOf(estado);
        values[2] = año;
        values[3] = numActuaciones;
        
        DenseInstance instance = new DenseInstance(1.0, values);
        instance.setDataset(dataset);
        
        return randomForest.classifyInstance(instance);
    }
}