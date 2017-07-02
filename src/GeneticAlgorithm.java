/**
 * Created by piyush on 29/6/17.
 */
public class GeneticAlgorithm {

    public static final int POPULATION_SIZE= 8;                             // Total Chromosomes in Population
    public static final int[] TARGET_CHROMOSOME = {1,1,0,1,0,0,1,1,1,0};    // Desired chromosome
    private static final double MUTATION_RATE = 0.25;                       // Denotes probability of Mutation of Chromosome
    public static final int NUMB_OF_ELITE_CHROMOSOMES = 1;                  // Denotes fittest(desirable) chromosomes
    public static final int TOURNAMENT_SELECTION_SIZE = 4;                  //

    // Evolve population for changes to approach towards solution.
    public Population evolvePopulation( Population population) {
        return mutatePopulation( crossOverPopulation( population ) );
    }

    // CrossOverPopulation should preserve best chromosome from previous generation.
    private Population crossOverPopulation( Population population){
        Population crossOverPopulation = new Population(population.getChromosomes().length);
        // To preserve
        for(int i=0;i<NUMB_OF_ELITE_CHROMOSOMES;i++) {
            crossOverPopulation.getChromosomes()[i] = population.getChromosomes()[i];
        }
        // Crossover Population (Chromosomes) arbitrarily which aren't fittest
        for(int i = NUMB_OF_ELITE_CHROMOSOMES;i < population.getChromosomes().length;i++){
            Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0];
            Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];
            crossOverPopulation.getChromosomes()[i] = crossOverChromosome(chromosome1,chromosome2);
        }
        return crossOverPopulation;
    }

    private Population mutatePopulation(Population population) {
        Population mutatePopulation = new Population(population.getChromosomes().length);
        // Preserves Fittest Chromosomes
        for(int i=0;i<NUMB_OF_ELITE_CHROMOSOMES;i++) {
            mutatePopulation.getChromosomes()[i] = population.getChromosomes()[i];
        }
        // Mutate Population (Chromosomes) which aren't Fittest
        for(int i = NUMB_OF_ELITE_CHROMOSOMES;i<population.getChromosomes().length;i++) {
            mutatePopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
        }
        return mutatePopulation;
    }

    private Chromosome crossOverChromosome(Chromosome chromosome1,Chromosome chromosome2){
        Chromosome crossOverChromosome = new Chromosome(TARGET_CHROMOSOME.length);
        for(int i =0;i<chromosome1.getGenes().length;i++) {

            // 50 % Probability to inherit either chromosome1 or chromosome2
            if(Math.random() < 0.5) crossOverChromosome.getGenes()[i] = chromosome1.getGenes()[i];
            else crossOverChromosome.getGenes()[i] = chromosome2.getGenes()[i];
        }
        return crossOverChromosome;
    }

    private Chromosome mutateChromosome( Chromosome chromosome ) {
        Chromosome mutateChromosome = new Chromosome(TARGET_CHROMOSOME.length);
        for(int i=0;i<chromosome.getGenes().length;i++) {

            // For probability of Mutation < MUTATION_RATE and 50 % probability of mutating to either 1 or 0
            if(Math.random() < MUTATION_RATE) {
                if(Math.random() < 0.5) mutateChromosome.getGenes()[i] = 1;
                else mutateChromosome.getGenes()[i] = 0;
            }
            else mutateChromosome.getGenes()[i] = chromosome.getGenes()[i];
        }
        return mutateChromosome;
    }

    // Select Chromosomes for crossover
    private Population selectTournamentPopulation( Population population ){
        Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);
        for(int i=0;i<TOURNAMENT_SELECTION_SIZE;i++) {
            tournamentPopulation.getChromosomes()[i] = population.getChromosomes()[ (int)(Math.random()*population.getChromosomes().length) ];
        }
        tournamentPopulation.sortChromosomesByFitness();
        return tournamentPopulation;
    }

}