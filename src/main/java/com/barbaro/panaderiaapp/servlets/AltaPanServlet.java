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

@WebServlet(name="AltalPanServlet", urlPatterns = {"/pan"})
public class AltaPanServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("action", "pan");
		req.setAttribute("method", "get");
		req.getRequestDispatcher("formPan.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String txtNombre = req.getParameter("txtNombre");
		String txtDescripcion = req.getParameter("txtDes");
		String txtTamanyo = req.getParameter("txtTamanyo");
		String txtPrecio = req.getParameter("txtPrecio");
		
		Connection conn = null;
		DatabaseManager dbManager = null;
		
		conn = DatabaseUtil.getConnection();
		
		if(conn != null) {
			
			// Crear objeto para el control de la bd
			dbManager = new DatabaseManager(conn);
			
			// Crear modelo y poner datos
			Pan pan = new Pan();
			pan.setNombre(txtNombre);
			pan.setDescripcion(txtDescripcion);
			pan.setTamanyo(txtTamanyo);
			pan.setPrecio(Float.parseFloat(txtPrecio));
			
			// Realizar operacion insert en la bd
			dbManager.addPan(pan);
			
			// Cerrar conexión
			DatabaseUtil.closeConnection(conn);
			
			// Rediregir a la vista de la lista de panes /panes
			resp.sendRedirect(getServletContext().getContextPath() + "/panes");
		} else {
			resp.setContentType("text/html");
			resp.getWriter().println("<h2>Fallo al insertar pan</h2>");
		}
	}
}








