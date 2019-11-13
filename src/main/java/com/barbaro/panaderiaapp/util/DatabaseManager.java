package com.barbaro.panaderiaapp.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.barbaro.panaderiaapp.models.Pan;

public class DatabaseManager {
	
	private Connection conn;
	
	public DatabaseManager(Connection conn) {
		this.conn = conn;
	}
	
	public int addPan(Pan pan) {
		String query = "INSERT INTO pan(nombre, descripcion, tamanyo, precio) "
				+ "VALUES(?, ?, ?, ?)";
		PreparedStatement stmnt = null;
		
		int result = -1;
		
		try {
			
			stmnt = conn.prepareStatement(query);
			
			stmnt.setString(1, pan.getNombre());
			stmnt.setString(2, pan.getDescripcion());
			stmnt.setString(3, pan.getTamanyo());
			stmnt.setFloat(4, pan.getPrecio());
			
			result = stmnt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt != null) {
				try {
					stmnt.close();
					System.out.println("Cerro el statement");
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public int updatePan(Pan pan) {
		String query = "UPDATE pan SET nombre=?, descripcion=?, tamanyo=?, precio=? "
				+ "WHERE id=?";
		PreparedStatement stmnt = null;
		int result = -1;
		
		try {
			
			stmnt = conn.prepareStatement(query);
			
			stmnt.setString(1, pan.getNombre());
			stmnt.setString(2, pan.getDescripcion());
			stmnt.setString(3, pan.getTamanyo());
			stmnt.setFloat(4, pan.getPrecio());
			stmnt.setInt(5, pan.getId());
			
			result = stmnt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt != null) {
				try {
					stmnt.close();
					System.out.println("Cerro el statement");
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} 
		return result;
	}
	
	public int deletePan(int id) {
		String query = "DELETE FROM pan WHERE id=?";
		PreparedStatement stmnt = null;
		int result = -1;
		
		try {
			
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, id);
			result = stmnt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmnt != null) {
				try {
					stmnt.close();
					System.out.println("Cerro el statement");
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public List<Pan> getPanes(){
		return null;
	}
	
	public Pan getPanById(int id) {
		String query = "SELECT * FROM pan WHERE id=?";
		PreparedStatement stmnt = null;
		ResultSet rs = null;
		Pan pan = null;
		
		try {
			stmnt = conn.prepareStatement(query);
			stmnt.setInt(1, id);
			rs = stmnt.executeQuery();
			if(rs.first()) {
				
				String nombre = rs.getString(2);
				String descripcion = rs.getString(6);
				String tamanyo = rs.getString(4);
				float precio = rs.getFloat(3);
				Date fecha = rs.getDate(5);
				pan = new Pan();
				
				pan.setId(id);
				pan.setNombre(nombre);
				pan.setDescripcion(descripcion);
				pan.setPrecio(precio);
				pan.setTamanyo(tamanyo);
				pan.setFecha(fecha);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo operacion con bd");
		} finally {
			if(rs != null) {
				try {
					rs.close();
					System.out.println("Cerro el resultset");
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmnt != null) {
				try {
					stmnt.close();
					System.out.println("Cerro el statement");
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return pan;
	}
}










