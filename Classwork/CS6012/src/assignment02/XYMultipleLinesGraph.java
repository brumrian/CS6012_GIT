package UsefullProgramsClasses;

import java.io.File;
import java.io.IOException;
import java.io.NotSerializableException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.jfree.chart.*;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.ui.RefineryUtilities;

public class XYMultipleLinesGraph extends ApplicationFrame {

    private ArrayList<Double> slope = new ArrayList<>();
    private ArrayList<Double> correlationCoefficient = new ArrayList<>();
    private JFreeChart xyLineChart;
    private String[] LegendValues;

    public XYMultipleLinesGraph(String applicationTitle, String chartTitle, String Xname, String Yname, String[] legendValues, ArrayList<double[]> Xdata, ArrayList<double[]> Ydata) throws IOException {
        super(applicationTitle);

        LegendValues = legendValues;

        if (Xdata.size() != Ydata.size() || Xdata.size() == 0) {
            throw new NotSerializableException();
        }

        xyLineChart = ChartFactory.createXYLineChart(
                chartTitle,
                Xname,
                Yname,
                createDataset(Xdata, Ydata, legendValues),
                PlotOrientation.VERTICAL,
                true, true, false);

        xyLineChart.setPadding(new RectangleInsets(0, 0, 0, 5));
        ChartPanel chartPanel = new ChartPanel(xyLineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        final XYPlot plot = xyLineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);
        setContentPane(chartPanel);

        determineSlope(Xdata, Ydata);
        determineCorrelationCoefficient(Xdata, Ydata);
    }

    private XYDataset createDataset(ArrayList<double[]> Xdata, ArrayList<double[]> Ydata, String[] legendVals) {
        ArrayList<XYSeries> dataSets = new ArrayList<XYSeries>();
        for (int i = 0; i < Xdata.size(); i++) {
            final XYSeries dataset = new XYSeries(legendVals[i]);
            double[] xdata = Xdata.get(i);
            double[] ydata = Ydata.get(i);
            for (int j = 0; j < xdata.length; j++) {
                dataset.add(xdata[j], ydata[j]);
            }
            dataSets.add(dataset);
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        for (XYSeries data : dataSets) {
            dataset.addSeries(data);
        }
        return dataset;
    }

    private void determineSlope(ArrayList<double[]> xdata, ArrayList<double[]> ydata) {
        for(int j=0; j<xdata.size(); j++) {
            double[] Xdata = xdata.get(j);
            double[] Ydata = ydata.get(j);
            double SLOPE = 0;
            for (int i = 0; i < (Xdata.length - 1); i++) {
                double y_2 = Ydata[i + 1];
                double y_1 = Ydata[i];

                double delta_y = y_2 - y_1;

                double x_2 = Xdata[i + 1];
                double x_1 = Xdata[i];

                double delta_x = x_2 - x_1;

                SLOPE += delta_y / delta_x;
            }
            SLOPE /= xdata.size();
            slope.add(SLOPE);
        }
    }

    private void determineCorrelationCoefficient(ArrayList<double[]> xdata, ArrayList<double[]> ydata) {
        for(int j=0; j<xdata.size(); j++) {
            double[] Xdata = xdata.get(j);
            double[] Ydata = ydata.get(j);

            double sum_X = 0, sum_Y = 0, sum_XY = 0;
            double squareSum_X = 0, squareSum_Y = 0;
            int n = xdata.size();
            for (int i = 0; i < n; i++) {
                // sum of elements of array X.
                sum_X = sum_X + Xdata[i];

                // sum of elements of array Y.
                sum_Y = sum_Y + Ydata[i];

                // sum of X[i] * Y[i].
                sum_XY = sum_XY + Xdata[i] * Ydata[i];

                // sum of square of array elements.
                squareSum_X = squareSum_X + Xdata[i] * Xdata[i];
                squareSum_Y = squareSum_Y + Ydata[i] * Ydata[i];
            }

            // use formula for calculating correlation
            // coefficient.
            double CORR = (double) (n * sum_XY - sum_X * sum_Y) /
                    (double) (Math.sqrt((n * squareSum_X -
                            sum_X * sum_X) * (n * squareSum_Y -
                            sum_Y * sum_Y)));

            correlationCoefficient.add(CORR * CORR);
        }
    }

    public double[] getLinSlope() {
        double[] Slope = new double[slope.size()];
        for(int i=0; i<slope.size(); i++){
            Slope[i]=slope.get(i);
        }
        return Slope;
    }

    public double[] getCor() {
        double[] corr = new double[correlationCoefficient.size()];
        for(int i=0; i<correlationCoefficient.size(); i++){
            corr[i]=correlationCoefficient.get(i);
        }
        return corr;
    }

    public String getSlopeAndCorr(){
        DecimalFormat df = new DecimalFormat("##.#####");
        StringBuilder val = new StringBuilder();
        double corr =0;
        double Slope =0;
        for(int i=0; i<correlationCoefficient.size(); i++){
            corr=correlationCoefficient.get(i);
            Slope=slope.get(i);
            val.append(LegendValues[i]).append(" Slope: ").append(df.format(Slope)).append(" Corr: ").append(df.format(corr)).append("\n");
        }
        return val.toString();
    }

    public void addSubtitle(String titleString) {
        TextTitle subTitle = new TextTitle(titleString);
        xyLineChart.addSubtitle(subTitle);
    }

    public void saveChart(String fileName) throws IOException {
        ChartUtilities.saveChartAsPNG(new File(fileName), xyLineChart, 450, 400);
    }

    /**  //comment out this line to make the example runnable ("command"+"/")
    //This is example code of how to implement the library
    public static void main(String[] args) throws IOException {
        double[] Xdata1 = new double[]{0, 1, 2, 3, 4, 5, 6};//first set of data x
        double[] Ydata1 = new double[]{6, 5, 4, 3, 2, 1, 0};//first set of data y
        double[] Xdata2 = new double[]{0, 1, 2, 3, 4, 5, 6};//second set of data x
        double[] Ydata2 = new double[]{0, 1, 2, 3, 4, 5, 6};//second set of data y
        ArrayList<double[]> Xdata = new ArrayList<double[]>();//X data Array list to be passed
        ArrayList<double[]> Ydata = new ArrayList<double[]>();//Y data Array list to be passed
        //just added two for demonstration, can add as many as you want.
        Xdata.add(Xdata1);//add x 1 to X data
        Xdata.add(Xdata2);//add x 2 to X data
        Ydata.add(Ydata1);//add y 1 to Y data
        Ydata.add(Ydata2);//add y 2 to Y data
        String[] legendVals = new String[]{"Line 1 yay", "this is another line"};//names of each set of data in order 1 then 2

        XYMultipleLinesGraph chart1 = new XYMultipleLinesGraph("TestMulti",//name of the window the graph is in
                "Time to run Sum on integer list Logarithmic",//name of the graph
                "Size of array (log(N))",//X label
                "Time (nanoseconds (log(t)))",//Y label
                legendVals,//String[] of names for the legend
                Xdata,//ArrayList<double[]> of x data, each item of the array is a whole data set
                Ydata);//ArrayList<double[]> of y data, each item of the array is a whole data set
        DecimalFormat df = new DecimalFormat("##.#####");
        chart1.addSubtitle("this is a subTitle");//make a subtitle (optional)
        System.out.println(chart1.getSlopeAndCorr());
        chart1.saveChart("src/extraFiles/test3.png");//the file location you want to save the png to (format must end in .png)
        chart1.pack();//compiles the graph
        RefineryUtilities.positionFrameOnScreen(chart1, 0.50, 0.50);//creates graph window in center of screen
        //you can change the percent vales to move the window if you make more than one and don't want them to overlap
        chart1.setVisible(true);//make the window visible
    }
//    */
}
