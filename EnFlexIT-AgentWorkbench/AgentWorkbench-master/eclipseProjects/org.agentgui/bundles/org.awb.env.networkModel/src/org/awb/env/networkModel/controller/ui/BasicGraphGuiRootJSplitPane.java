/**
 * ***************************************************************
 * Agent.GUI is a framework to develop Multi-agent based simulation 
 * applications based on the JADE - Framework in compliance with the 
 * FIPA specifications. 
 * Copyright (C) 2010 Christian Derksen and DAWIS
 * http://www.dawis.wiwi.uni-due.de
 * http://sourceforge.net/projects/agentgui/
 * http://www.agentgui.org 
 *
 * GNU Lesser General Public License
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation,
 * version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 * **************************************************************
 */

package org.awb.env.networkModel.controller.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import org.awb.env.networkModel.GraphGlobals;
import org.awb.env.networkModel.NetworkComponent;
import org.awb.env.networkModel.controller.GraphEnvironmentController;
import org.awb.env.networkModel.controller.NetworkModelNotification;
import org.awb.env.networkModel.controller.ui.commands.RenamedNetworkComponent;
import org.awb.env.networkModel.settings.GeneralGraphSettings4MAS.ComponentSorting;
import org.awb.env.networkModel.settings.ui.TableCellEditor4TableButton;
import org.awb.env.networkModel.settings.ui.TableCellRenderer4Button;

import agentgui.core.application.Application;
import agentgui.core.application.Language;

/**
 * The GUI for a GraphEnvironmentController. This contains a pane showing the NetworkComponents table and the BasicGraphGUI. 
 * The main class which associates with the components table, the environment model and the Basic Graph GUI.
 * 
 * @see GraphEnvironmentController
 * @see BasicGraphGui
 * @see org.awb.env.networkModel.helper
 * 
 * @author Nils Loose - DAWIS - ICB University of Duisburg - Essen
 * @author Satyadeep Karnati - CSE - Indian Institute of Technology - Guwahati
 * @author Christian Derksen - DAWIS - ICB University of Duisburg - Essen
 */
public class BasicGraphGuiRootJSplitPane extends JInternalFrame implements ListSelectionListener, TableModelListener, Observer {

    private static final long serialVersionUID = 7376906096627051173L;

    private final static String pathImage = GraphGlobals.getPathImages();
    private String newLine = Application.getGlobalInfo().getNewLineSeparator();
    
    private GraphEnvironmentController graphController;
    private NetworkComponent currNetworkComponent;
    
    private JSplitPane jSplitPaneRoot;
    
    private JPanel jPanelControls;
    private JScrollPane jScrollPaneComponentsTable;
    private JLabel jLabelTable;
    private JTextField jTextFieldSearch;
    private JButton jButtonClearSearch;

    private JTable jTableComponents;
    private DefaultTableModel componentsTableModel;
    private TableRowSorter<DefaultTableModel> jTableRowSorter;
    private ComponentSorting componentSorting;
    private RowFilter<DefaultTableModel, Integer> jTableRowFilter;
    private boolean quiteTabelModelListener;
    
    private BasicGraphGui graphGUI;

    
    
    /**
     * This is the default constructor for just displaying the current environment model during a running simulation.
     * @param graphController the current {@link GraphEnvironmentController}
     */
    public BasicGraphGuiRootJSplitPane(GraphEnvironmentController graphController) {
		this.graphController = graphController;
		this.graphController.addObserver(this);
		this.initialize();
    }
    
    /**
     * This method initializes this
     * @return void
     */
    private void initialize() {
	
		this.setLayout(new BorderLayout());
		
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentPane(this.getJSplitPaneRoot());
		this.setNumberOfComponents();
		
		this.setVisible(true);
		
    }
    /**
     * Returns the graph environment controller.
     * @return the graph environment controller
     */
    private GraphEnvironmentController getGraphController() {
    	return this.graphController;
    }

	/* (non-Javadoc)
	 * @see agentgui.core.environment.EnvironmentPanel#dispose()
	 */
	@Override
	public void dispose() {
		if (this.graphGUI!=null) {
			// --- Destroy the current GraphGui -----
			this.graphGUI.dispose();
			this.graphGUI = null;
		}
	}
    
    /**
     * This method initializes jSplitPaneRoot
     * @return javax.swing.JSplitPane
     */
    public JSplitPane getJSplitPaneRoot() {
		if (jSplitPaneRoot == null) {
		    jSplitPaneRoot = new JSplitPane();
		    jSplitPaneRoot.setOneTouchExpandable(true);
		    jSplitPaneRoot.setLeftComponent(this.getJPanelControlls());
		    jSplitPaneRoot.setRightComponent(this.getBasicGraphGui());
		    jSplitPaneRoot.setDividerLocation(230);
		}
		return jSplitPaneRoot;
    }
    /**
     * Gets the BasicGraphGui, which is the visualization component for the graph.
     * @return the basic graph GUI that contains the graph visualization component
     */
    public BasicGraphGui getBasicGraphGui() {
		if (graphGUI == null) {
		    graphGUI = new BasicGraphGui(this.getGraphController());
		}
		return graphGUI;
    }
    
    /**
     * This method initializes pnlControlls
     * @return javax.swing.JPanel
     */
    private JPanel getJPanelControlls() {
		if (jPanelControls == null) {
		    GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		    gridBagConstraints1.gridx = 1;
		    gridBagConstraints1.insets = new Insets(0, 0, 0, 5);
		    gridBagConstraints1.gridy = 1;
		    GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		    gridBagConstraints11.fill = GridBagConstraints.BOTH;
		    gridBagConstraints11.gridy = 1;
		    gridBagConstraints11.weightx = 0.5;
		    gridBagConstraints11.gridwidth = 1;
		    gridBagConstraints11.insets = new Insets(0, 10, 0, 1);
		    gridBagConstraints11.gridx = 0;
		    GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		    gridBagConstraints6.gridx = 0;
		    gridBagConstraints6.anchor = GridBagConstraints.WEST;
		    gridBagConstraints6.insets = new Insets(0, 15, 0, 5);
		    gridBagConstraints6.gridwidth = 2;
		    gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
		    gridBagConstraints6.gridy = 0;
		    GridBagConstraints gridBagConstraints = new GridBagConstraints();
		    gridBagConstraints.fill = GridBagConstraints.BOTH;
		    gridBagConstraints.gridy = 2;
		    gridBagConstraints.weightx = 1.0;
		    gridBagConstraints.weighty = 1.0;
		    gridBagConstraints.gridheight = 1;
		    gridBagConstraints.gridwidth = 0;
		    gridBagConstraints.insets = new Insets(0, 10, 0, 5);
		    gridBagConstraints.gridx = 0;

		    jLabelTable = new JLabel();
		    jLabelTable.setFont(new Font("Dialog", Font.BOLD, 12));
		    jLabelTable.setText("Search Components");
		    jLabelTable.setText(Language.translate(jLabelTable.getText(), Language.EN));

		    jPanelControls = new JPanel();
		    jPanelControls.setLayout(new GridBagLayout());
		    jPanelControls.add(jLabelTable, gridBagConstraints6);
		    jPanelControls.add(getJTextFieldSearch(), gridBagConstraints11);
		    jPanelControls.add(getScpComponentTable(), gridBagConstraints);
		    jPanelControls.add(getJButtonClearSearch(), gridBagConstraints1);
		}
		return jPanelControls;
    }
    /**
     * This method initializes scpComponentTable
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getScpComponentTable() {
		if (jScrollPaneComponentsTable == null) {
		    jScrollPaneComponentsTable = new JScrollPane();
		    jScrollPaneComponentsTable.setViewportView(this.getJTableComponents());
		}
		return jScrollPaneComponentsTable;
    }
    
    /**
     * This method initializes network components table tblComponents and the TabelModel
     * @return javax.swing.JTable
     */
    private JTable getJTableComponents() {
		if (jTableComponents == null) {
		    jTableComponents = new JTable(this.getDefaultTableModel4Components());
		    jTableComponents.setFillsViewportHeight(true);
		    jTableComponents.setShowGrid(false);
		    jTableComponents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    jTableComponents.getTableHeader().setReorderingAllowed(false);
		    
		    jTableComponents.getModel().addTableModelListener(this);
		    jTableComponents.getSelectionModel().addListSelectionListener(this);
		    jTableComponents.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent me) {
		    		if (me.getClickCount()==2) {
		    			getGraphController().getNetworkModelUndoManager().zoomNetworkComponent();
		    		}
		    	}
			});
		    
			// --- Define the column widths -------------------
			TableColumnModel colModel = jTableComponents.getColumnModel();
			colModel.getColumn(0).setPreferredWidth(40);
			colModel.getColumn(0).setCellRenderer(new BasicGraphGuiTableCellRenderEditor());
			colModel.getColumn(0).setCellEditor(new BasicGraphGuiTableCellRenderEditor());			
			
			colModel.getColumn(1).setPreferredWidth(40);
			colModel.getColumn(1).setCellRenderer(new BasicGraphGuiTableCellRenderEditor());
			
			colModel.getColumn(2).setWidth(50);
			colModel.getColumn(2).setMinWidth(50);
			colModel.getColumn(2).setMaxWidth(60);
			colModel.getColumn(2).setCellRenderer(new TableCellRenderer4Button());
			colModel.getColumn(2).setCellEditor(new TableCellEditor4TableButton(this.getGraphController(), jTableComponents));			
			

			// --- Set the table sorter -----------------------
			TableRowSorter<DefaultTableModel> tblSorter = this.getJTableRowSorter();
			jTableComponents.setRowSorter(tblSorter);

			// --- Define the first sort order ----------------
			List<SortKey> sortKeys = new ArrayList<SortKey>();
			for (int i = 0; i < jTableComponents.getColumnCount(); i++) {
			    sortKeys.add(new SortKey(i, SortOrder.ASCENDING));
			}
			tblSorter.setSortKeys(sortKeys);
			
		}
		return jTableComponents;
    }
    /**
     * Returns the DefaultTableModel for the network components.
     * @return the DefaultTableModel for the network components
     */
    private DefaultTableModel getDefaultTableModel4Components() {
    	if (componentsTableModel==null) {
    		
    	    Vector<String> titles = new Vector<String>();
    	    titles.add("ID");
    	    titles.add(Language.translate("Typ"));
    	    titles.add(Language.translate("Edit", Language.EN));

    	    // --- Set DataVector -----------------------------------
    	    componentsTableModel = new DefaultTableModel(null, titles) {
    			private static final long serialVersionUID = 1636744550817904118L;
    			@Override
    			public boolean isCellEditable(int row, int col) {
    			    if (col==1) {
    			    	return false;
    			    } else {
    			    	if (getGraphController().getProject()!=null) {
    			    		// --- During Simulation Setup ----------
    			    		return true;	
    			    	} else {
    			    		// --- During simulation runtime --------
    			    		if (col==0) {
    			    			return false;
    			    		} else {
    			    			return true;
    			    		}
    			    	}
    			    }
    			}
    	    };
    		
    	}
    	return componentsTableModel;
    }
    
    /**
     * Returns the table row sorter.
     * @return the table row sorter
     */
    private TableRowSorter<DefaultTableModel> getJTableRowSorter() {
    	if (jTableRowSorter==null) {
    		// --- Set Sorter for the table -------------------
			jTableRowSorter = new TableRowSorter<DefaultTableModel>(this.getDefaultTableModel4Components()) {
				@Override
				public void sort() {
					// --- may throw a "java.lang.IllegalArgumentException: Comparison method violates its general contract!" ---
					try {
						BasicGraphGuiRootJSplitPane.this.updateComponentSorting();
						super.sort();
						
					} catch (IllegalArgumentException iaEx) {
						iaEx.printStackTrace();
					}
				}
			};
		   
		    // --- Define a comparator for the first row ------
			jTableRowSorter.setComparator(0, new Comparator<String>() {
				@Override
				public int compare(String netCompId1, String netCompId2) {
					if (BasicGraphGuiRootJSplitPane.this.componentSorting==ComponentSorting.Alphanumeric) {
						Long ncID1 = this.parseLong(netCompId1);
						Long ncID2 = this.parseLong(netCompId2);
						if (ncID1!=null && ncID2!=null) {
							return ncID1.compareTo(ncID2);
						} else if (ncID1==null & ncID2!=null) {
							return -1;
						} else if (ncID1!=null & ncID2==null) {
							return 1;
						}							
					}
					return netCompId1.compareToIgnoreCase(netCompId2);
				}
				private Long parseLong(String netCompID) {
					Long longValue = null;
					String ncIdNumberString = netCompID.replaceAll("\\D+","");
					if (ncIdNumberString.length()>0) {
						try {
							longValue = Long.parseLong(ncIdNumberString);
						} catch (NumberFormatException nfEx) {
							// nfEx.printStackTrace();
						}
					}
					return longValue;
				}
			});
			jTableRowSorter.setRowFilter(this.getJTableRowFilter());
    	}
    	return jTableRowSorter;
    }
    /**
     * Return the row filter.
     * @return the row filter
     */
    private RowFilter<DefaultTableModel, Integer> getJTableRowFilter() {
    	if (jTableRowFilter==null) {
    		jTableRowFilter = new RowFilter<DefaultTableModel, Integer>() {
    			@Override
    			public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
    				
    				// --- Return true if the search phrase is empty ----
    				String searchPhrase = BasicGraphGuiRootJSplitPane.this.getJTextFieldSearch().getText().trim();
    				if (searchPhrase==null || searchPhrase.length()==0) return true;
    				
    				// --- Check for the search phrase ------------------
					boolean matchNetComID = entry.getStringValue(0).matches("(?i).*(" + searchPhrase + ").*");
					boolean matchNetComType = entry.getStringValue(1).matches("(?i).*(" + searchPhrase + ").*");
					return matchNetComID==true || matchNetComType==true;
    			}
    		};
        	    		
    	}
    	return jTableRowFilter;
    }
    /**
     * This method builds the tblComponents' contents based on the controllers GridModel
     * @return The grid components' IDs and class names
     */
    private void fillTableModel() {
		if (this.getGraphController().getNetworkModel()!=null && this.getGraphController().getNetworkModelUndoManager()!=null ) {
			// --- Clear ------------------------
			this.clearTableModel();
			// --- Fill -------------------------
			networkComponentAdd(new ArrayList<>(this.getGraphController().getNetworkModel().getNetworkComponents().values()));
		}
    }
    /**
     * Clears the table model.
     */
    private void clearTableModel() {
    	quiteTabelModelListener = true;
    	this.getDefaultTableModel4Components().getDataVector().removeAllElements();
    	this.getDefaultTableModel4Components().fireTableDataChanged();
    	quiteTabelModelListener = false;
    }
    
    /* (non-Javadoc)
     * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    @Override
    public void valueChanged(ListSelectionEvent le) {
		if (this.getJTableComponents().getSelectedRowCount() > 0) {
		    // Converting from view coordinates to model coordinates
		    int selectedIndex = getJTableComponents().convertRowIndexToModel(getJTableComponents().getSelectedRow());
		    String componentID = (String) jTableComponents.getModel().getValueAt(selectedIndex, 0);
		    this.currNetworkComponent = this.getGraphController().getNetworkModel().getNetworkComponent(componentID);
		    this.getGraphController().getNetworkModelUndoManager().selectNetworkComponent(this.currNetworkComponent);	
		}
    }
    /*
     * (non-Javadoc)
     * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event. TableModelEvent)
     */
    @Override
    public void tableChanged(TableModelEvent tme) {

    	if (this.quiteTabelModelListener==true) return;
    	
		int row = tme.getFirstRow();
		int column = tme.getColumn();
	
		DefaultTableModel model = (DefaultTableModel) tme.getSource();
		if (column == 0 && row >= 0 && row < model.getRowCount()) {
	
		    String oldCompID = this.currNetworkComponent.getId();
			String newCompID = (String) model.getValueAt(row, column);
		    if (oldCompID.equals(newCompID)==false) {
	
		    	String message = null;
		    	String title = "Warning";
		    	
				if (newCompID == null || newCompID.length() == 0) {
				    // --- Check if the component id is empty
					message = "Enter a valid name";
				    JOptionPane.showMessageDialog(this, Language.translate(message, Language.EN), Language.translate(title, Language.EN), JOptionPane.WARNING_MESSAGE);
				    getJTableComponents().getModel().setValueAt(oldCompID, row, column);
		
				} else if (newCompID.contains(" ")) {
				    // --- Check for spaces
					message = "Enter the name without spaces";
				    JOptionPane.showMessageDialog(this, Language.translate(message, Language.EN), Language.translate(title, Language.EN), JOptionPane.WARNING_MESSAGE);
				    getJTableComponents().getModel().setValueAt(oldCompID, row, column);
		
				} else if (getGraphController().getNetworkModel().getNetworkComponent(newCompID)!=null) {
				    // --- Check if a network component name already exists
					message = "The component name already exists!" + newLine + "Choose a different one.";
				    JOptionPane.showMessageDialog(this, Language.translate(message, Language.EN), Language.translate(title, Language.EN), JOptionPane.WARNING_MESSAGE);
				    getJTableComponents().getModel().setValueAt(oldCompID, row, column);
		
				} else if (this.getGraphController().getProject().getSimulationSetups().getCurrSimSetup().isAgentNameExists(newCompID)) {
				    // --- Check if the agent name already exists in the simulation setup
					message = "An agent with the name already exists in the simulation setup!" + newLine + " Choose a different one.";
					JOptionPane.showMessageDialog(this, Language.translate(message, Language.EN), Language.translate(title, Language.EN), JOptionPane.WARNING_MESSAGE);
				    getJTableComponents().getModel().setValueAt(oldCompID, row, column);
		
				} else {
				    // --- All validations done, rename the component and update the network model
					// --- renaming NetworkComponents and GraphElements
					this.getGraphController().getNetworkModelUndoManager().renameNetworkComponent(oldCompID, newCompID);
				}
		    }
		}
		// System.out.println(row+","+column);
    }
   
    /**
     * Given a network component, selects the corresponding row in the components display table
     * @param networkComponent
     */
    private void networkComponentSelect(NetworkComponent networkComponent) {
		
    	if (networkComponent==null) return;
    	if (networkComponent.getId()==null) return;
    	
    	int rowCount = this.getDefaultTableModel4Components().getRowCount();
		int row = -1;
		// Searching all the rows in the table
		for (row = 0; row < rowCount; row++) {
		    String compId = (String) getJTableComponents().getModel().getValueAt(row, 0);
		    if (compId!=null) {
			    // Checking for the matching component Id
			    if (compId.equals(networkComponent.getId())) {
					// Converting from model coordinates to view coordinates
					int viewRowIndex = getJTableComponents().convertRowIndexToView(row);
					int viewColumnIndex = getJTableComponents().convertColumnIndexToView(0);
					boolean toggle = false;
					boolean extend = false;
					// Selecting the cell in the table
					this.getJTableComponents().changeSelection(viewRowIndex, viewColumnIndex, toggle, extend);
					break;
			    }
		    }
		}
    }
    
    /**
     * Adds a new network component to the list of components in the table.
     * @param networkComponent the network component
     */
    private void networkComponentAdd(NetworkComponent networkComponent) {
    	this.networkComponentAdd(networkComponent, true);
    }
    /**
     * Adds a new network component to the list of components in the table.
     *
     * @param networkComponent the network component
     * @param updateNumberOfComponents the update number of components
     */
    private void networkComponentAdd(NetworkComponent networkComponent, boolean updateNumberOfComponents) {
		
    	Vector<String> compData = new Vector<String>();
		compData.add(networkComponent.getId());
		compData.add(networkComponent.getType());
		compData.add("Edit"); // For the edit properties button
		this.getDefaultTableModel4Components().addRow(compData);
		if (updateNumberOfComponents==true) {
			this.setNumberOfComponents();
		}
    }
    /**
     * Adds a new network component to the list of components in the table.
     * @param netCompList the network component list
     */
    private void networkComponentAdd(List<NetworkComponent> netCompList) {
    	this.quiteTabelModelListener = true;
    	for (int i = 0; i < netCompList.size(); i++) {
			this.networkComponentAdd(netCompList.get(i), false);
		}
    	this.quiteTabelModelListener = false;
    	this.setNumberOfComponents();
    }
    
    
    /**
     * Removes a network component from the list of components in the table.
     * @param networkComponent the network component
     */
    private void networkComponentRemove(NetworkComponent networkComponent) {
    	this.networkComponentRemove(networkComponent, true);
    }
    /**
     * Removes a network component from the list of components in the table.
     *
     * @param networkComponent the network component
     * @param updateNumberOfComponents the update number of components
     */
    private void networkComponentRemove(NetworkComponent networkComponent, boolean updateNumberOfComponents) {
    	int rowCount = this.getDefaultTableModel4Components().getRowCount();
    	for (int row = 0; row < rowCount; row++) {
    		String entry = (String) this.getDefaultTableModel4Components().getValueAt(row, 0);
    		if (entry.equals(networkComponent.getId())) {
    			this.getDefaultTableModel4Components().removeRow(row);
    			if (updateNumberOfComponents==true) {
    				this.setNumberOfComponents();
    			}
    			return;
    		}
		}
    }
    /**
     * Removes a set of NetworkComponent's from the list of components in the table.
     * @param networkComponents the network components
     */
    private void networkComponentRemove(List<NetworkComponent> netCompList) {
    
    	// --- Get a list of all NetworkComponent Id's ----
    	HashSet<String> networkComponentIDs = new HashSet<String>();
    	for (int i = 0; i < netCompList.size(); i++) {
    		networkComponentIDs.add(netCompList.get(i).getId());
		}
    	
    	for (int row = 0; row<this.getDefaultTableModel4Components().getRowCount(); row++) {
    		String entry = (String) this.getDefaultTableModel4Components().getValueAt(row, 0);
    		if (networkComponentIDs.contains(entry)) {
    			this.getDefaultTableModel4Components().removeRow(row);
    			row--;
    		}
		}
    	this.setNumberOfComponents();
    	
    }
    
    
    /**
     * The action, when a Network component was renamed.
     * @param renamedNetworkComponent the renamed network component
     */
    private void renamedNetworkComponent(RenamedNetworkComponent renamedNetworkComponent) {
    	
    	if (renamedNetworkComponent==null) return;
    	
    	String oldID = renamedNetworkComponent.getOldNetworkComponentID();
    	String newID = renamedNetworkComponent.getNewNetworkComponentID();
    	
    	int rowCount = this.getDefaultTableModel4Components().getRowCount();
		int row = -1;
		// --- Searching all the rows in the table ----------------------------
		for (row = 0; row < rowCount; row++) {
		    String compId = (String) getJTableComponents().getModel().getValueAt(row, 0);
		    if (compId!=null) {
			    // --- Checking for the matching to the old ID ----------------
			    if (compId.equals(oldID)) {
			    	// --- Set the new ID in the table model ------------------
			    	this.quiteTabelModelListener = true;
			    	this.getJTableComponents().getModel().setValueAt(newID, row, 0);
			    	this.quiteTabelModelListener = false;
			    	// --- Set focus on component -----------------------------
					int viewRowIndex = getJTableComponents().convertRowIndexToView(row);
					int viewColumnIndex = getJTableComponents().convertColumnIndexToView(0);
					boolean toggle = false;
					boolean extend = false;
					// --- Select cell in the table ---------------------------
					this.getJTableComponents().changeSelection(viewRowIndex, viewColumnIndex, toggle, extend);
					break;
			    }
		    }
		}
    }
    

    /**
     * This method initializes jTextFieldSearch Search box - Used for filtering the components
     * @return javax.swing.JTextField
     */
    private JTextField getJTextFieldSearch() {
		if (jTextFieldSearch == null) {
		    jTextFieldSearch = new JTextField();
		    jTextFieldSearch.setPreferredSize(new Dimension(100, 20));
		    jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
				@Override
				public void keyReleased(java.awt.event.KeyEvent e) {
					BasicGraphGuiRootJSplitPane.this.applyTableSorter();
				}
		    });
		}
		return jTextFieldSearch;
    }
    /**
     * This method initializes jButtonClearSearch
     * @return javax.swing.JButton
     */
    private JButton getJButtonClearSearch() {
		if (jButtonClearSearch == null) {
		    jButtonClearSearch = new JButton();
		    jButtonClearSearch.setPreferredSize(new Dimension(16, 20));
		    jButtonClearSearch.setToolTipText("Clear search");
		    jButtonClearSearch.setIcon(new ImageIcon(this.getClass().getResource(pathImage + "ClearSearch.png")));
		    jButtonClearSearch.addActionListener(new ActionListener() {
		    	@Override
				public void actionPerformed(ActionEvent e) {
				    BasicGraphGuiRootJSplitPane.this.getJTextFieldSearch().setText(null);
				    BasicGraphGuiRootJSplitPane.this.applyTableSorter();
				}
		    });
		}
		return jButtonClearSearch;
    }
    
    /**
     * Applies the table sorter again.
     */
	public void applyTableSorter() {
		try {
			this.getJTableRowSorter().sort();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		this.setNumberOfComponents();

    }
    /**
     * Updates the setting for the component sorting.
     */
    private void updateComponentSorting() {
    	this.componentSorting = BasicGraphGuiRootJSplitPane.this.getGraphController().getGeneralGraphSettings4MAS().getComponentSorting();
    }
    /**
     * ReLoads the network model.
     */
    private void reLoadNetworkComponents() {
    	
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// --- (Re)fill the table model -----------
				BasicGraphGuiRootJSplitPane.this.fillTableModel();
				// --- Clear search field -----------------
			    BasicGraphGuiRootJSplitPane.this.getJTextFieldSearch().setText(null);
			    BasicGraphGuiRootJSplitPane.this.applyTableSorter();
			}
		});
    }
    
    /**
     * Set / show number of components.
     */
    private void setNumberOfComponents() {
		// --- Set the number of rows displayed -----------
		String text = this.jLabelTable.getText();
		if (text.indexOf("(") > -1) {
		    text = text.substring(0, text.indexOf("(")).trim();
		}
		text = text + " (" + jTableComponents.getRowCount() + ")";
		this.jLabelTable.setText(text);
    }
    
    /*
     * (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable observable, Object object) {
    	
    	if (object instanceof NetworkModelNotification) {
    		
    		NetworkModelNotification nmNotification = (NetworkModelNotification) object;
    		int reason = nmNotification.getReason();
    		Object infoObject = nmNotification.getInfoObject();
    		NetworkComponent networkComponent = null;
    		
    		switch (reason) {
    		case NetworkModelNotification.NETWORK_MODEL_ComponentTypeSettingsChanged:
    		case NetworkModelNotification.NETWORK_MODEL_Reload:
    		case NetworkModelNotification.NETWORK_MODEL_Merged_With_Supplement_NetworkModel:
    			this.reLoadNetworkComponents();
    			this.applyTableSorter();
    			break;
				
    		case NetworkModelNotification.NETWORK_MODEL_Component_Added:
    			if (infoObject instanceof NetworkComponent) {
    				networkComponent = (NetworkComponent) infoObject;
    				this.networkComponentAdd(networkComponent);	
    			} else if (infoObject instanceof HashSet<?>) {
					@SuppressWarnings("unchecked")
					HashSet<NetworkComponent> networkComponentHash = (HashSet<NetworkComponent>) infoObject;
					this.networkComponentAdd(new ArrayList<>(networkComponentHash));
    			}
				break;
				
			case NetworkModelNotification.NETWORK_MODEL_Component_Removed:
				if (infoObject instanceof NetworkComponent) {
					networkComponent = (NetworkComponent) infoObject;
					this.networkComponentRemove(networkComponent);
				
				} else if (infoObject instanceof List<?>) {
					@SuppressWarnings("unchecked")
					List<NetworkComponent> networkComponentList = (List<NetworkComponent>) infoObject;
					this.networkComponentRemove(networkComponentList);
				}
				break;
			
			case NetworkModelNotification.NETWORK_MODEL_Component_Select:
				networkComponent = (NetworkComponent) infoObject;
				this.networkComponentSelect(networkComponent);
				break;
				
			case NetworkModelNotification.NETWORK_MODEL_EditComponentSettings:
				break;
			
			case NetworkModelNotification.NETWORK_MODEL_Component_Renamed:
				RenamedNetworkComponent renamed = (RenamedNetworkComponent) infoObject;
				this.renamedNetworkComponent(renamed);
				break;
				
			case NetworkModelNotification.NETWORK_MODEL_GraphMouse_EdgeEditing:
				// --- Disable to edit elements in the table --------
				this.getJTableComponents().setEnabled(false);
				break;
			case NetworkModelNotification.NETWORK_MODEL_GraphMouse_Picking:
				// --- Enable to edit elements in the table ---------
				this.getJTableComponents().setEnabled(true);
				break;
				
			default:
				break;
			}
    	}
    	
    }

}  
