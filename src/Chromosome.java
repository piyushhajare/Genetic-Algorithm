import java.util.Arrays;

/**
 * Created by Piyush Hajare on 29/6/17.
 */
public class Chromosome {

    private boolean isFitnessChanged = true;
    private int fitness = 0;
    private int[] genes;

    // Constructor creates new gene of that chromosome
    public Chromosome(int length) {
        genes = new int[length];
    }

    // Randomly assigns the genes of chromosomes, Returns 'this' Chromosome.
    public Chromosome initializeChromosome() {
        for(int x=0;x<genes.length;x++) {
            if(Math.random() >= 0.5) genes[x] = 1;
            else genes[x] = 0;
        }
        return this;
    }

    // Return genes and denotes changed fitness.
    public int[] getGenes() {
        isFitnessChanged = true;
        return genes;
    }

    // If fitness is changed it recalculates it to save computation.
    public int getFitness(){
        if(isFitnessChanged) {
            fitness = recalculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }

    // Number of elements in gene same as ideal gene is fitness
    public int recalculateFitness(){
        int chromosomeFitness = 0;
        for(int i=0; i<genes.length;i++) {
            if(genes[i] == GeneticAlgorithm.TARGET_CHROMOSOME[i]) chromosomeFitness++;
        }
        return chromosomeFitness;
    }

    // Stringify
    public String toString() {
        return Arrays.toString(this.genes);
    }

}
