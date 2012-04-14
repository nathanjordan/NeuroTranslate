package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gdk.EventExpose;
import org.gnome.gdk.EventFocus;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.Entry.Activate;
import org.gnome.gtk.TreeSelection.Changed;

import unr.neurotranslate.ncs.Column;
import unr.neurotranslate.ncs.ColumnShell;
import unr.neurotranslate.ncs.Layer;
import unr.neurotranslate.ncs.NCSData;
import unr.neurotranslate.ui.controller.UIControllerNCS;

public class ColumnHandler {
	
	// All array lists are for debugging
	public ArrayList<String> cShells;
	public ArrayList<String> col;
	public ListEntity columnShells;
	public ListEntity columns;
	public ListEntity layers;
	public ComboEntity colShellSel;
	public Entry csType;
	public Entry csWidth;
	public Entry csHeight;
	public Entry csLocX;
	public Entry csLocY;
	public Entry cType;
	public String selectedText;
	public ColumnShell currentColumnShell;
	public Column currentColumn;
	public Layer currentLayer;
	public TreeSelection rs1;
	public TreeSelection rs2;
	public TreeSelection rowSelection3;
	public UIControllerNCS u;
	public ColumnHandler() throws Exception {
		
		ScrolledWindow c = (ScrolledWindow) GladeParseUtil.grabWidget("scrolledwindow6", "window1");
		u = new UIControllerNCS();
		c.connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes
				 
				
				return false;
			}
		});
		
		// Data sources
		cShells = new ArrayList<String>();
		col = new ArrayList<String>();
		selectedText = new String();
		
		// Entries		
		csType = (Entry) GladeParseUtil.grabWidget( "csType", "window1" );
		csWidth = (Entry) GladeParseUtil.grabWidget( "csWidth", "window1" );
		csHeight = (Entry) GladeParseUtil.grabWidget( "csHeight", "window1" );
		csLocX = (Entry) GladeParseUtil.grabWidget( "csLocX", "window1" );
		csLocY = (Entry) GladeParseUtil.grabWidget( "csLocY", "window1" );	
		cType = (Entry) GladeParseUtil.grabWidget( "entry12", "window1" );
		
		columnShells = new ListEntity( "cColShells", "window1" ); 
		columns = new ListEntity( "cColumnView", "window1" );
		colShellSel = new ComboEntity( "combobox2", "window1" );						
		layers = new ListEntity( "lLayerList", "window1" );
		
		// Initialize all lists
		columnShells.listToModel( u.getColumnShells() );		
		columns.listToModel(u.getColumns());					
		
		setLists();
		
		setEntries();
		
		modifyHandlers();
	}

	private void setEntries() throws FileNotFoundException {		
				
		// Entries are set depending on current column shell selected
		TreeView colsView = columnShells.getView();		
		
		// Connect for getting selected row in tree view		
		rs1 = colsView.getSelection();
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected column shell
					selectedText = columnShells.getModel().getValue(rs1.getSelected(), columnShells.getHeader());
								
					// Get current column shell based on selected column shell
					try {
					//	currentColumnShell = u.getColumnShellByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// Set everything else to current column shell				
					/*csType.setText(currentColumnShell.type);
					csWidth.setText(currentColumnShell.width.toString());
					csHeight.setText(currentColumnShell.height.toString());
					csLocX.setText(currentColumnShell.x.toString());
					csLocY.setText(currentColumnShell.y.toString()); 
					*/					
				}							
			}
		});		

		// Entries are set depending on current column selected
		TreeView colView = columns.getView();		
		
		// Connect for getting selected row in tree view		
		rs2 = colView.getSelection();
		rs2.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs2.getSelected() != null ) {
					
					// Get selected column
					selectedText = columns.getModel().getValue(rs2.getSelected(), columns.getHeader());
					
					// Get current column shell based on selected column 
					try {
						// currentColumn = u.getColumnByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current column 	
					// cType.setText(currentColumn.type);
				}							
			}
		});
		
		// TODO - Figure out how to update this list
		TreeView layView = layers.getView();		
		
		// Connect for getting selected row in tree view		
		rowSelection3 = layView.getSelection();
		rowSelection3.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rowSelection3.getSelected() != null ) {
					
					// Get selected layer type
					selectedText = layers.getModel().getValue(rowSelection3.getSelected(), layers.getHeader());
									
				}							
			}
		});

	}

	private void setLists() throws FileNotFoundException {				 			
		

			
	}

	
	private void modifyHandlers() throws FileNotFoundException {
	
		// Buttons for adding/removing
		Button addColShells = (Button) GladeParseUtil.grabWidget( "cShellAdd", "window1" );
		Button removeColShells = (Button) GladeParseUtil.grabWidget( "cShellRem", "window1" );
		Button addCol = (Button) GladeParseUtil.grabWidget( "cColAdd", "window1" );
		Button removeCol= (Button) GladeParseUtil.grabWidget( "cColRem", "window1" );
		
		// Connect for adding a column shell
		addColShells.connect( new Clicked() {
			int counter = 0;
			@Override
			public void onClicked(Button arg0) {
				
				counter++;
				// Add a new column shell to the model, update data source??
				columnShells.addData( "NewColumnShell" + counter );
				
			}
		});
		
		// Connect for removing a column shell
		removeColShells.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				// Remove selected column shell
				columnShells.removeData();
				csType.setText( "" );
				
			}
		});
		
		// Connect for adding a column 
		addCol.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Add a new column
				columns.addData( "NewColumn" );				
			
			}
		});
				
		// Connect for remove a column 
		removeCol.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Remove selected column
				columns.removeData();
			}
		});
	}
}

