package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Editable;
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
import unr.neurotranslate.ui.controller.UIControllerNCS;

public class ColumnHandler {

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
				w.getL( "coColumns").listToModel( w.getL("bColTypes").getAvailable() );				
				
				if( w.getC("coColShellSel").getChanged() ){
					w.getC("coColShellSel").listToModel(ui.getColumnShells());
					w.getC("coColShellSel").setChanged(false);
				}
				
				return false;
			}
		});
				
		setEntries(w, ui);
		
		modifyHandlers(w, ui);
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
	
	private void modifyHandlers( final WidgetReferences w, final UIControllerNCS ui ) throws FileNotFoundException {	
		
		// Connect for adding a column shell
		((Button)w.getW("coAddCShell")).connect( new Clicked() {		
			@Override
			public void onClicked(Button arg0) {

				currentColumnShell = ui.addColumnShell();			
				currentColumnShell.width = currentColumnShell.height = currentColumnShell.x = currentColumnShell.y = 0.0;				
				w.getL("coColShells").addData( currentColumnShell.type );
				w.getC("coColShellSel").setChanged(false);
				
			}
		});
		
		// Connect for removing a column shell
		((Button)w.getW("coRemCShell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				// Remove selected column shell
				try {
					ui.removeColumnShell(w.getL("coColShells").getSelected());
				} catch (Exception e) {
				
					e.printStackTrace();
				}				
				w.getL("coColShells").removeData();	
				w.getC("coColShellSel").setChanged(false);
				
				((Entry) w.getW("coCSType")).setText( " " );
				((Entry) w.getW("coWidth")).setText( " " );
				((Entry) w.getW("coHeight")).setText( " " );
				((Entry) w.getW("coLocX")).setText( " " );
				((Entry) w.getW("coLocY")).setText( " " );	
			
			}
		});
		
		// Connect for adding a column 
		((Button)w.getW("coAddColumn")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Add a new column
				currentColumn = ui.addColumn();
				w.getL("coColumns").addData( currentColumn.type );		
				w.getL("bColTypes").addAvailable(currentColumn.type);
				w.getC("stColSel").setChanged(true);
				w.getC("rColSel").setChanged(true);
			}
		});
				
		// Connect for remove a column 
		((Button)w.getW("coRemColumn")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Remove selected column
				try {
					ui.removeColumn(w.getL("coColumns").getSelected());
				} catch (Exception e) {					
					e.printStackTrace();
				}
				w.getL("coColumns").removeData();
				((Entry) w.getW("coCType")).setText( " " );
				w.getC("stColSel").setChanged(true);
				w.getC("rColSel").setChanged(true);
				
			}
		});
		
		// Connect for modifying layer types
		((Button)w.getW("coModLayer")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Show popup and update the views
				w.getW("popup").show();
				//ModifyPopup.update( "Layer Types", "coLayers", w, ui );
				
			}
		});
		
		// Column Shell Type	
		((Entry) w.getW("coCSType")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {						
				w.getL("coColShells").removeData();		
				w.getL("coColShells").addData(arg0.getText());	
				w.getL("coColShells").getView().grabFocus();
				currentColumnShell.type = arg0.getText();				
			}
		});
		
		// Column Shell Width
		((Entry) w.getW("coWidth")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentColumnShell.width = d;
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
			}
		});
		
		// Column Shell Height
		((Entry) w.getW("coHeight")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentColumnShell.height = d;
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
			}
		});
		
		// Column Shell Location X
		((Entry) w.getW("coLocX")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentColumnShell.x = d;
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
			}
		});
		
		// Column Shell Location Y
		((Entry) w.getW("coLocY")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentColumnShell.y = d;				
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
				}
			}
		});
		
		// Column Type	
		((Entry) w.getW("coCType")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {						
				w.getL("coColumns").removeData();		
				w.getL("coColumns").addData(arg0.getText());	
				w.getL("coColumns").getView().grabFocus();
				currentColumn.type = arg0.getText();				
			}
		});
	}
}

