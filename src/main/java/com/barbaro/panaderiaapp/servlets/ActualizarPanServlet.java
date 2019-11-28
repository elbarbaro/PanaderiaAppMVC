package com.barbaro.panaderiaapp.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barbaro.panaderiaapp.models.Pan;
import com.barbaro.panaderiaapp.util.DatabaseManager;
import com.barbaro.panaderiaapp.util.DatabaseUtil;

@WebServlet(name = "ActualizarPanServlet", urlPatterns = {"/pan/actualizar"})
public class ActualizarPanServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String txtId = req.getParameter("txtId");
		Pan pan = null;
		Connection conn = null;
		DatabaseManager dbManager = null;
		
		conn = DatabaseUtil.getConnection();
		dbManager = new DatabaseManager(conn);
		pan = dbManager.getPanById(Integer.parseInt(txtId));
		
		DatabaseUtil.closeConnection(conn);
		
		req.setAttribute("pan", pan);
		req.setAttribute("action", getServletContext().getContextPath()+"/pan/actualizar");
		req.setAttribute("actionMessage", "Actualizar");
		req.getRequestDispatcher("/formPan.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Obtener los valores
		String txtId = req.getParameter("txtId");
		String txtNombre = req.getParameter("txtNombre");
		String txtDes = req.getParameter("txtDes");
		String txtTamanyo = req.getParameter("txtTamanyo");
		float precio = Float.parseFloat(req.getParameter("txtPrecio"));
		
		Connection conn = null;
		DatabaseManager dbManager = null;
		Pan pan = null;
		
		try {
			
			conn = DatabaseUtil.getConnection();
			dbManager = new DatabaseManager(conn);
			
			pan = new Pan();
			pan.setId(Integer.parseInt(txtId));
			pan.setNombre(txtNombre);
			pan.setDescripcion(txtDes);
			pan.setTamanyo(txtTamanyo);
			pan.setPrecio(precio);
			
			dbManager.updatePan(pan);
			
			DatabaseUtil.closeConnection(conn);
			resp.sendRedirect(getServletContext().getContextPath()+"/panes");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
