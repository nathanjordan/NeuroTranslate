package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.TreeSelection.Changed;

import unr.neurotranslate.ncs.Column;
import unr.neurotranslate.ncs.ColumnShell;
import unr.neurotranslate.ui.controller.UIControllerNCS;

public class ColumnHandler {
	
	// All array lists are for debugging
	public ArrayList<String> cShells;
	public ArrayList<String> col;	
	public String selectedText;	
	public TreeSelection rs1, rs2, rs3;
	public ColumnShell currentColumnShell;
	public Column currentColumn;
	
	public ColumnHandler(final WidgetReferences w, final UIControllerNCS ui) throws Exception {
		
		
		w.getW("columnScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// Refresh lists
				w.getL( "coColShells").listToModel( ui.getColumnShells() );
				w.getL( "coColumns").listToModel( ui.getColumns() );					
				return false;
			}
		});
				
		setEntries(w, ui);
		
		modifyHandlers(w);
	}

	private void setEntries(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {		
				
		// Entries are set depending on current column shell selected
		TreeView colsView = w.getL("coColShells").getView();
		
		// Connect for getting selected row in tree view		
		rs1 = colsView.getSelection();
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected column shell				
					selectedText = w.getL("coColShells").getModel().getValue(rs1.getSelected(), w.getL("coColShells").getHeader());					
					
					// Get current column shell based on selected column shell
					try {
						currentColumnShell = ui.getColumnShellByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// Set everything else to current column shell				
					((Entry) w.getW("coCSType")).setText(selectedText);					
					((Entry) w.getW("coWidth")).setText(currentColumnShell.width.toString());
					((Entry) w.getW("coHeight")).setText(currentColumnShell.height.toString());
					((Entry) w.getW("coLocX")).setText(currentColumnShell.x.toString());
					((Entry) w.getW("coLocY")).setText(currentColumnShell.y.toString()); 								
				}							
			}
		});		

		// Entries are set depending on current column selected
		TreeView colView = w.getL("coColumns").getView();		
		
		// Connect for getting selected row in tree view		
		rs2 = colView.getSelection();
		rs2.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs2.getSelected() != null ) {
					
					// Get selected column
					selectedText = w.getL("coColumns").getModel().getValue(rs2.getSelected(), w.getL("coColumns").getHeader());
					
					// Get current column shell based on selected column 
					try {
						 currentColumn = ui.getColumnByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current column 	
					((Entry) w.getW("coCType")).setText(currentColumn.type);
				}							
			}
		});
		
		// TODO - Figure out how to update this list
		TreeView layView = w.getL("coLayers").getView();		
		
		// Connect for getting selected row in tree view		
		rs3 = layView.getSelection();
		rs3.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs3.getSelected() != null ) {
					
					// Get selected layer type
					selectedText = w.getL("coLayers").getModel().getValue(rs3.getSelected(), w.getL("coLayers").getHeader());
									
				}							
			}
		});

	}

	
	private void modifyHandlers( final WidgetReferences w ) throws FileNotFoundException {	
		
		// Connect for adding a column shell
		((Button)w.getW("coAddCShell")).connect( new Clicked() {
			int counter = 0;
			@Override
			public void onClicked(Button arg0) {
				
				counter++;
				// Add a new column shell to the model, update data source??
				w.getL("coColShells").addData( "NewColumnShell" + counter );
				
			}
		});
		
		// Connect for removing a column shell
		((Button)w.getW("coRemCShell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				// Remove selected column shell
				w.getL("coColShells").removeData();
				((Entry) w.getW("coType")).setText( "" );
				
			}
		});
		
		// Connect for adding a column 
		((Button)w.getW("coAddColumn")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Add a new column
				w.getL("coColumns").addData( "NewColumn" );				
			
			}
		});
				
		// Connect for remove a column 
		((Button)w.getW("coRemColumn")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Remove selected column
				w.getL("coColumns").removeData();
				
			}
		});
	}
}

