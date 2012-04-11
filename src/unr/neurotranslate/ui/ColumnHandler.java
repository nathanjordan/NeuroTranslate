package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.Entry.Activate;
import org.gnome.gtk.TreeSelection.Changed;

import unr.neurotranslate.ncs.ColumnShell;
import unr.neurotranslate.ncs.NCSData;

public class ColumnHandler {
	
	// All array lists are for debugging
	public ArrayList<String> cShells;
	public ArrayList<String> col;
	public ListEntity columnShells;
	public ListEntity columns;
	public ComboEntity colShellSel;
	public Entry csType;
	public Entry csWidth;
	public Entry csHeight;
	public Entry csLocX;
	public Entry csLocY;
	public String selectedText;
	
	public ColumnHandler() throws FileNotFoundException {
		
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
		
		setLists();
		
		setEntries();
		
		modifyHandlers();
	}

	private void setEntries() throws FileNotFoundException {
	
		//final ArrayList<ColumnShell> c = new ArrayList<ColumnShell>(d.columnShellList);
				
		// Entries are set depending on current column shell selected
		TreeView colView = columnShells.getView();		
		
		// Connect for getting selected row in tree view		
		final TreeSelection rowSelection = colView.getSelection();
		rowSelection.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rowSelection.getSelected() != null ) {
					
					// Set entries to selected column shell data 
					csType.setText(columnShells.getModel().getValue(rowSelection.getSelected(), columnShells.getHeader()));
					
					selectedText = csType.getText();
					
					/*for( int i = 0; i < c.size(); i++ ) {
						if( selectedText.equals(c.get(i).type ) ) {
							csWidth.setText( Double.toString(c.get(i).width) );
						}
						else {
							csWidth.setText("");
						}
					}*/
					
				}							
			}
		});		

		
		
		// Update data model corresponding to user input
		csType.connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				// Update selected data in list...
				
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

