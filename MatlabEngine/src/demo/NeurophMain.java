/***
 * The Example is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Example is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Neuroph. If not, see <http://www.gnu.org/licenses/>.
 */

package demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.LMS;

/**
 *
 * @author Dr.V.Steinhauer
 */
public class NeurophMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Time stamp N1:" + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));

        int maxIterations = 100000;
        NeuralNetwork neuralNet = new MultiLayerPerceptron(4, 9, 1);
        ((LMS) neuralNet.getLearningRule()).setMaxError(0.001);//0-1
        ((LMS) neuralNet.getLearningRule()).setLearningRate(0.7);//0-1
        ((LMS) neuralNet.getLearningRule()).setMaxIterations(maxIterations);
        TrainingSet trainingSet = new TrainingSet();

        // US cents per pound of chicken - 2008,2009
        double[] data = {77.77D, 76.85D, 77.25D, 79.15D, 81.23D, 82.04D, 83.46D, 85.71D, 88.25D, 88.42D, 88.40D, 87.54D, //2008
            87.02D, 87.25D, 86.7D, 85.73D, 85.38D, 86.96D, 88.17D, 88.56D, 86.77D, 82.85D, 82.13D};//2009

        // task: will be the chicken more expensive as today?
        double datamax = -9999.0D;
        double datamin = 9999.0D;
        for (int i = 0; i < 22; i++) {

            if (data[i] > datamax) {
                datamax = data[i];
            }
            if (data[i] < datamin) {
                datamin = data[i];
            }
        }

        datamax = datamax * 1.2D;
        datamin = datamin * 0.8D;

        for (int i = 0; i < data.length - 5; i++) {
            System.out.println(data[i] + " " + data[i + 1] + " " + data[i + 2] + " " + data[i + 3] + "->" + data[i + 4]);
            trainingSet.addElement(new SupervisedTrainingElement(new double[]{(data[i] - datamin) / datamax, (data[i + 1] - datamin) / datamax, (data[i + 2] - datamin) / datamax, (data[i + 3] - datamin) / datamax}, new double[]{(data[i + 4] - datamin) / datamax}));
        }

        System.out.println("Training network please wait...");

        neuralNet.learnInSameThread(trainingSet);

        System.out.println("Time stamp N2:" + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));

        TrainingSet testSet = new TrainingSet();
        testSet.addElement(new TrainingElement(new double[]{(88.17D - datamin) / datamax, (88.56D - datamin) / datamax, (86.77D - datamin) / datamax, (82.85D - datamin) / datamax}));
        double middle = (88.17D + 88.56D + 86.77D + 82.85D) / 4.0D;
        for (TrainingElement testElement : testSet.trainingElements()) {
            neuralNet.setInput(testElement.getInput());
            neuralNet.calculate();
            Vector<Double> networkOutput = neuralNet.getOutput();
            //System.out.print("Input: " + testElement.getInput()+"\n");//to test
            //System.out.println(" Output: " + networkOutput);//to test
            double pred = (networkOutput.elementAt(0)) * datamax + datamin;
            System.out.printf("Middle price of chicken for last 4 months =%4.2f(US cents per pound)\nPredicted price of chicken for next month =%4.2f(US cents per pound)\n", middle, pred);
            if (pred < middle) {
                System.out.print("You can buy. It will not be expensive.)");
            } else {
                System.out.print("Chicken might be more expensive!");
            }
        }


        System.out.println("Time stamp N3:" + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));
        System.exit(0);

    }
}
