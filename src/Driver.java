import java.util.Arrays;

/**
 * Created by piyush on 29/6/17.
 */
public class Driver {

    public static void main(String[] args){

        Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        System.out.println("---------------------------------");
        System.out.println("Generation # 0 " + " | Fittest Chromosome Fitness : " + population.getChromosomes()[0].getFitness());
        printPopulation(population,"Target Chromosome " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
        int generationNumber = 0;
        while(population.getChromosomes()[0].getFitness() < GeneticAlgorithm.TARGET_CHROMOSOME.length) {
//            generationNumber++;
            System.out.println("\n\n" + "---------------------------------");
            population = geneticAlgorithm.evolvePopulation(population);
            population.sortChromosomesByFitness();
            System.out.println("Generation # " + generationNumber + " "  + " | Fittest Chromosome Fitness : " + population.getChromosomes()[0].getFitness());
            printPopulation(population,"Target Chromosome " + Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME));
            generationNumber++;

        }

    }

    public static void printPopulation( Population population, String heading ) {
        System.out.println(heading);
        System.out.println("---------------------------------");
        for(int x=0;x<population.getChromosomes().length;x++) {
            System.out.println("Chromosome # " + x + " : " + Arrays.toString( population.getChromosomes()[x].getGenes() )
             + " | Fitness : " + population.getChromosomes()[x].getFitness());

        }
    }

}