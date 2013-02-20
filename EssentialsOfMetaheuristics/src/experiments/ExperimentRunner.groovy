package experiments

import populationMethods.GeneticAlgorithm
import problems.HIFF
import problems.LeadingOnes
import problems.LeadingOnesBlocks
import problems.OnesMax
import problems.Trap
import singleStateMethods.HillClimber
import singleStateMethods.SteepestAscentHillClimber
import singleStateMethods.SteepestAscentHillClimberWithReplacement
import operators.*

class ExperimentRunner {

    static runExperiment(searchers, problems, selectors, crossovers, numRuns = 30) {
        for (p in problems) {
            for (s in searchers) {
                for(selector in selectors){
                    for(c in crossovers){
                        for (i in 0..<numRuns) {
                            p.evalCount = 0
                            def result = s.maximize(p, 100, selector, c.value)
                            println "${s.toString()}\t${selector.toString()}\t${c.key}\t${p.toString()}\t${p.quality(result)}\t${result}"
                        }
                    }
                }
            }
        }
    }

            static main(args) {
                def crossovers = [
                   "1PtXo" : new Crossovers().onePointCrossover,
                   "2PtXo" : new Crossovers().twoPointCrossover,
                   "UniXo" : new Crossovers().uniformCrossover
                    ]
                def selectors = [
                    new TournamentSelection(tournamentSize : 2),
                    new TournamentSelection(tournamentSize : 4),
                    new FitnessProportionateSelection()
                    ]
                def searchers = [
                    new GeneticAlgorithm()
                ]
                def problems = [
                    //			new OnesMax(numBits : 100, maxIterations : 250),
                    //			new LeadingOnes(numBits : 100, maxIterations : 1000),
                    //			new LeadingOnesBlocks(numBits : 100, maxIterations : 10000, blockSize : 1),
                    //			new LeadingOnesBlocks(numBits : 100, maxIterations : 10000, blockSize : 2),
                    //			new LeadingOnesBlocks(numBits : 100, maxIterations : 10000, blockSize : 4),
                    //			new Trap(numBits : 4, maxIterations : 1000),
                    //			new Trap(numBits : 8, maxIterations : 1000),
                    //			new Trap(numBits : 16, maxIterations : 1000),
                    new HIFF(numBits : 4, maxIterations : 1000),
                    new HIFF(numBits : 8, maxIterations : 1000),
                    new HIFF(numBits : 16, maxIterations : 1000),
                    new HIFF(numBits : 32, maxIterations : 1000)
                ]
                // It would be nice to collect the total time here and include it in the
                // output.
                runExperiment(searchers, problems, selectors, crossovers)
            }
        }
