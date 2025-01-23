package minimarketdemo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

public class BarCharts {
	
	/***
	 * 
	 * @param <T>     Tipo placeholder, puede ser cualquier objeto
	 * @param <R>     Es la clase de los atributo de los items de la lista
	 * @param list    => Lista de objetos del EJB
	 * @param valores => Atributo de valores numéricos
	 * @param texto   => Atributo de valores Strings
	 * @return
	 */
	public static <T, R> BarChartModel crearChart(List<T> list, Function<T, Number> valores, Function<T, String> texto) {
		BarChartModel barModel = new BarChartModel();
		ChartData data = new ChartData();

		BarChartDataSet barDataSet = new BarChartDataSet();
		barDataSet.setLabel("Facturas por mes");

		List<Number> values = extraerAtributos(list, valores);

		barDataSet.setData(values);

		List<String> bgColor = new ArrayList<>();
		List<String> borderColor = new ArrayList<>();
		agregarColores(bgColor, borderColor, list.size());

		barDataSet.setBackgroundColor(bgColor);
		barDataSet.setBorderColor(borderColor);
		barDataSet.setBorderWidth(1);

		data.addChartDataSet(barDataSet);

		List<String> labels = extraerAtributos(list, texto);
		data.setLabels(labels);

		// Data
		barModel.setData(data);

		// Options
		BarChartOptions options = new BarChartOptions();
		CartesianScales cScales = new CartesianScales();
		CartesianLinearAxes linearAxes = new CartesianLinearAxes();
		CartesianLinearTicks ticks = new CartesianLinearTicks();
		ticks.setBeginAtZero(true);
		linearAxes.setTicks(ticks);
		cScales.addYAxesData(linearAxes);
		options.setScales(cScales);

		Title title = new Title();
		title.setDisplay(true);
		title.setText("Modulo facturacion");
		options.setTitle(title);

		Legend legend = new Legend();
		legend.setDisplay(true);
		legend.setPosition("top");
		LegendLabel legendLabels = new LegendLabel();
		legendLabels.setFontStyle("bold");
		legendLabels.setFontColor("#2980B9");
		legendLabels.setFontSize(24);
		legend.setLabels(legendLabels);
		options.setLegend(legend);

		barModel.setOptions(options);
		return barModel;
	}
	
	// Método genérico que extrae los atributos de un objeto y retorna una lista de
	// los atributos
	private static <T, R> List<R> extraerAtributos(List<T> objects, Function<T, R> extractor) {
		List<R> atributo = new ArrayList<>();
		for (T obj : objects) {
			atributo.add(extractor.apply(obj));
		}
		return atributo;
	}

	// Agrega colores a los charts.
	// Puede aumentarse más colores.
	private static void agregarColores(List<String> bgColor, List<String> borderColor, int size) {
		List<String> colorOptionsBg = List.of("rgba(255, 99, 132, 0.2)", // Rojo
				"rgba(255, 159, 64, 0.2)", // Naranja
				"rgba(75, 192, 192, 0.2)" // Verde
		);

		List<String> colorOptionsBorder = List.of("rgb(255, 99, 132)", // Rojo
				"rgb(255, 159, 64)", // Naranja
				"rgb(75, 192, 192)" // Verde
		);

		for (int i = 0; i < size; i++) {
			int colorIndex = i % colorOptionsBg.size();
			bgColor.add(colorOptionsBg.get(colorIndex));
			borderColor.add(colorOptionsBorder.get(colorIndex));
		}
	}
}
