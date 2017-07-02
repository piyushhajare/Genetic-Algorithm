import java.util.Arrays;

/**
 * Created by piyush on 29/6/17.
 */
public class Population {

    private Chromosome[] chromosomes;
    public Population(int length) {     // Creates new Population of Chromosomes
        chromosomes = new Chromosome[length];
    }

    // Creates genes randomly in each chromosome
    public Population initializePopulation() {
        for(int i=0;i<chromosomes.length;i++){
            chromosomes[i] = new Chromosome(GeneticAlgorithm.TARGET_CHROMOSOME.length).initializeChromosome();
        }
        sortChromosomesByFitness();
        return this;
    }

    public Chromosome[] getChromosomes(){
        return chromosomes;
    }

    public void sortChromosomesByFitness(){     // Uses Override + Lambda function to sort acc. Fitness
        Arrays.sort(chromosomes,(chromosome1, chromosome2)->{
            int flag = 0;
            if(chromosome1.getFitness() > chromosome2.getFitness()) flag = -1;
            else if(chromosome1.getFitness() < chromosome2.getFitness()) flag = 1;
            return flag;
        });
    }
}
