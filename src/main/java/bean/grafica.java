/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import clases.puntajes;
import dao.PartidaDAO;
import javax.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author azul-
 */
@Named(value = "grafica")
@SessionScoped
@Data
@AllArgsConstructor
@NoArgsConstructor
@ManagedBean
public class grafica implements Serializable {
    private BarChartModel barModel;
    private BarChartModel barModel2;
    
    public BarChartModel getBarModel(){
        return barModel;
    }
    
    public void setBarModel(BarChartModel barModel){
        this.barModel = barModel;
    }
    public BarChartModel getBarModel2(){
        return barModel2;
    }
    
    public void setBarModel2(BarChartModel barModel){
        this.barModel2 = barModel;
    }
    
    private void createBarModels(){
        createBarModel();
        createBarModel2();
    }
    
    
    
    public List lista(){
        PartidaDAO dao = new PartidaDAO();
        List<puntajes> dto = null;
        dto = dao.puntajes();
        return dto;
    }
    
    @PostConstruct
    public void init(){
        createBarModels();
    }

    private void createBarModel() {
        barModel = initBarModel();
        barModel.setTitle("Puntajes Por Usuario");
        barModel.setLegendPosition("ne");
        
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Usuario");
        
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Score");
        yAxis.setMin(0);
        yAxis.setMax(400);
        
        
    }
    

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries puntajes = new BarChartSeries();
        List <puntajes> dto = new ArrayList<>();
        dto = lista();
        puntajes.setLabel("Puntajes");
        for(puntajes p : dto){
            puntajes.set(p.getNombre(), p.getPuntaje());
        }
        model.addSeries(puntajes);
        return model;
    }
    private void createBarModel2() {
        barModel2 = initBarModel2();
        barModel2.setTitle("Eliminaciones Por Usuario");
        barModel2.setLegendPosition("ne");
        
        Axis xAxis = barModel2.getAxis(AxisType.X);
        xAxis.setLabel("Usuario");
        
        Axis yAxis = barModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Eliminados");
        yAxis.setMin(0);
        yAxis.setMax(10);
        
        
    }
    

    private BarChartModel initBarModel2() {
        BarChartModel model = new BarChartModel();
        ChartSeries puntajes = new BarChartSeries();
        List <puntajes> dto = new ArrayList<>();
        dto = lista();
        puntajes.setLabel("Eliminaciones");
        for(puntajes p : dto){
            System.out.println("Eliminados " + p.getEliminados());
            puntajes.set(p.getNombre(), p.getEliminados());
        }
        model.addSeries(puntajes);
        return model;
    }
    
 }
