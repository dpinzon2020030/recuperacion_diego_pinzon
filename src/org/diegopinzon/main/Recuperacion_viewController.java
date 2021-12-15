/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.diegopinzon.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Diego Pinzon
 */
public class Recuperacion_viewController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TableColumn<Materia, Integer> colId;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<Materia, String> colCatedratico;
    @FXML
    private TableColumn<Materia, String> colSalon;
    @FXML
    private TableColumn<Materia, Integer> colCiclo_Escolar;
    @FXML
    private TableColumn<Materia, Integer> colCupo_Maximo;
    @FXML
    private TextField tfCatedratico;
    @FXML
    private TextField tfSalon;
    @FXML
    private TextField tfCiclo_Escolar;
    @FXML
    private TextField tfCupo_Maximo;
    @FXML
    private TableView<Materia> tvMateria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showMateria();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnInsert) {
            insertRecord();
        }
        
        if (event.getSource() == btnUpdate) {
            updateRecord();
        }
        
        if (event.getSource() == btnDelete) {
            deleteRecord();
        }
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/in5bm_recuperacion", "root", "adminval");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public ObservableList<Materia> getMateriaList() {
        ObservableList<Materia> materiaList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM materia";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Materia materia;
            while (rs.next()) {
                materia = new Materia(rs.getInt("id"), rs.getString("nombre"), rs.getInt("ciclo_escolar"), rs.getString("horario_inicio"), rs.getString("horario_final"), rs.getString("catedratico"), rs.getString("salon"), rs.getInt("cupo_maximo"), rs.getInt("cupo_minimo"));
                materiaList.add(materia);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return materiaList;
    }

    public void showMateria() {
        ObservableList<Materia> list = getMateriaList();

        colId.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("id"));
        colCatedratico.setCellValueFactory(new PropertyValueFactory<Materia, String>("catedratico"));
        colSalon.setCellValueFactory(new PropertyValueFactory<Materia, String>("salon"));
        colCiclo_Escolar.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("ciclo_escolar"));
        colCupo_Maximo.setCellValueFactory(new PropertyValueFactory<Materia, Integer>("cupo_maximo"));

        tvMateria.setItems(list);
    }

    private void insertRecord() {
        String query = "INSERT INTO materia (id,catedratico,salon,ciclo_escolar,cupo_maximo)"
                + " VALUES(" + tfId.getText() + ",'" + tfCatedratico.getText() + "','" + tfSalon.getText() + "'," + tfCiclo_Escolar.getText() + "," + tfCupo_Maximo.getText() + ")";
        executeQuery(query);
        showMateria();
    }

    private void updateRecord() {
        String query = "UPDATE materia"
                + " SET catedratico = '" + tfCatedratico.getText() + "'"
                + ",salon = '" + tfSalon.getText() + "'"
                + ",ciclo_escolar = " + tfCiclo_Escolar.getText()
                + ",cupo_maximo = " + tfCupo_Maximo.getText()
                + " WHERE id = " + tfId.getText();
        executeQuery(query);
        showMateria();
    }

    private void deleteRecord() {
        String query = "DELETE FROM materia"
                + " WHERE id = " + tfId.getText();
        executeQuery(query);
        showMateria();
    }
    
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Materia materia = tvMateria.getSelectionModel().getSelectedItem();
        
        System.out.println("id" + materia.getId());
        tfId.setText("" + materia.getId());
        tfCatedratico.setText(materia.getCatedratico());
        tfSalon.setText(materia.getSalon());
        tfCiclo_Escolar.setText("" + materia.getCiclo_escolar());
        tfCupo_Maximo.setText("" + materia.getCupo_maximo());
    }
}
