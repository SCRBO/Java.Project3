package bsu.rfe.java.group10.lab3.laptsinski.varB3;

import javax.swing.table.AbstractTableModel;
@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    public GornerTableModel(Double from, Double to, Double step,
                            Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    public int getColumnCount() {
// В данной модели два столбца
        return 3;
    }
    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return new Double (Math.ceil((to-from)/step)).intValue()+1;
    }
    public Object getValueAt(int row, int col) {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        Double result = 0.0;
        double x = from + step * row;
        for (int i = 0; i < coefficients.length; i++) {
            result = result * x + coefficients[i];
        }
        if (col == 0) {
            // Если запрашивается значение 1-го столбца, то это X
            return x;
        }

        else if (col == 1) {
            // Если запрашивается значение 2-го столбца, то это значение многочлена
            return result;
        }

        else {
            String strNumber = String.format("%.5f", result);



            if (Character.isDigit(strNumber.charAt(0)) && strNumber.charAt(0) == strNumber.charAt(strNumber.length() - 1)) {
                        return '✅';
                }

            else {
                return '❌';
            }
        }
    }
    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";
                case 1:
                    return "Значение многочлена";
            default:
                        return "Две пары";

        }

    }
    public Class<?> getColumnClass(int col) {
       if(col == 0 || col == 1) {
           return Double.class;
       }
       else {
           return boolean.class;
       }
    }
}
