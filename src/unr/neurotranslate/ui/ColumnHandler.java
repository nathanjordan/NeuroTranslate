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
	public String selectedText;
	public ColumnShell currentColumnShell;
	public Column currentColumn;
	public Layer currentLayer;
	public TreeSelection rowSelection;
	
	public ColumnHandler() throws FileNotFoundException {
		
		ScrolledWindow c = (ScrolledWindow) GladeParseUtil.grabWidget("scrolledwindow6", "window1");
		
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
		
		// Lists
		/*for( ColumnShell c : d.columnShellList ) {
			cShells.add(c.type);
		}*/
		
		columnShells = new ListEntity( "cColShells", "window1" ); 
		columns = new ListEntity( "cColumnView", "window1" );
		colShellSel = new ComboEntity( "combobox2", "window1" );						
		layers = new ListEntity( "lLayerList", "window1" );
		
		setLists();
		
		setEntries();
		
		modifyHandlers();
	}

	private void setEntries() throws FileNotFoundException {		
				
		// Entries are set depending on current column shell selected
		TreeView colsView = columnShells.getView();		
		
		// Connect for getting selected row in tree view		
		rowSelection = colsView.getSelection();
		rowSelection.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rowSelection.getSelected() != null ) {
					
					// Get selected column shell
					selectedText = columnShells.getModel().getValue(rowSelection.getSelected(), columnShells.getHeader());
					
					// Get current column shell based on selected column shell
						// currentColumnShell = colShellGetter(selectedText);									
					
					// Set everything else to current column shell				
			
				}							
			}
		});		

		// Entries are set depending on current column selected
		TreeView colView = columns.getView();		
		
		// Connect for getting selected row in tree view		
		rowSelection = colView.getSelection();
		rowSelection.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rowSelection.getSelected() != null ) {
					
					// Get selected column
					selectedText = columns.getModel().getValue(rowSelection.getSelected(), columns.getHeader());

					// Get current column shell based on selected column 
						// currentColumn = colGetter(selectedText);									
				
					// Set everything else to current column 	
				}							
			}
		});
		
		// Entries are set depending on current column selected
		TreeView layView = layers.getView();		
		
		// Connect for getting selected row in tree view		
		rowSelection = layView.getSelection();
		rowSelection.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rowSelection.getSelected() != null ) {
					
					// Get selected column
					selectedText = layers.getModel().getValue(rowSelection.getSelected(), layers.getHeader());

					// Get current column shell based on selected column 
						// currentColumn = colGetter(selectedText);									
				
					// Set everything else to current column 	
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

