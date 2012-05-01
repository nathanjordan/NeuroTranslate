package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.Entry.Activate;
import org.gnome.gtk.Notebook.ChangeCurrentPage;
import org.gnome.gtk.TreeSelection.Changed;

import unr.neurotranslate.ncs.Cell;
import unr.neurotranslate.ncs.Layer;
import unr.neurotranslate.ncs.LayerShell;
import unr.neurotranslate.ui.controller.UIControllerNCS;

public class LayerHandler {
	
	public TreeSelection rs1, rs2;
	public String selectedText;
	public LayerShell currentLayerShell;
	public Layer currentLayer;
	public Cell currentCell;
	
	public LayerHandler(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {
		
		// Set initial lists
		w.getL("lCells").setAvailable(ui.getCells());
		
		w.getW("layerScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes
				w.getL("lLayShells").listToModel( ui.getLayerShells() );
				w.getL("lLayers").listToModel( ui.getLayers() );				
				w.getL("lCells").listToModel( w.getL("lCells").getAvailable() );
				
				if(w.getC("lShellSel").getChanged() ){
					w.getC("lShellSel").listToModel(ui.getLayerShells());
					w.getC("lShellSel").setChanged(false);
				}

				return false;
			}
		});

		setEntries(w, ui);
		
		modifyHandlers(w, ui);
		
	}
	
	public void setEntries(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {
	
		// Entries are set depending on current layer shell selected
		TreeView layShView = w.getL("lLayShells").getView();		
		
		// Connect for getting selected row in tree view		
		rs1 = layShView.getSelection();
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected layer shell
					selectedText = w.getL("lLayShells").getModel().getValue(rs1.getSelected(), w.getL("lLayShells").getHeader());

					// Get current layer shell based on selected layer shell 
					 try {
						currentLayerShell = ui.getLayerShellByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current 
					if(currentLayerShell.type != null )
						((Entry) w.getW("lLSType")).setText(currentLayerShell.type);
					else
						((Entry) w.getW("lLSType")).setText("");
					((Entry) w.getW("lLSLower")).setText(currentLayerShell.lower.toString());
					((Entry) w.getW("lLSUpper")).setText(currentLayerShell.upper.toString());
				}							
			}
		});

	
		// Entries are set depending on current layer selected
		TreeView layView = w.getL("lLayers").getView();		
		
		// Connect for getting selected row in tree view		
		rs2 = layView.getSelection();
		rs2.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs2.getSelected() != null ) {
					
					// Get selected layer 
					selectedText = w.getL("lLayers").getModel().getValue(rs2.getSelected(), w.getL("lLayers").getHeader());

					// Get current layer based on selected 
					try {
						currentLayer = ui.getLayerByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current column
					((Entry) w.getW("lLType")).setText(currentLayer.type);
				}							
			}
		});
	}
	
	public void modifyHandlers(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {	
		
		// Connect for adding a Layer Shell
		((Button) w.getW("lAddLShell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				currentLayerShell = ui.addLayerShell();
				currentLayerShell.lower = currentLayerShell.upper = 0.0;
				w.getL("lLayShells").addData( currentLayerShell.type );
				w.getC("lShellSel").setChanged(true);
				
			}
		});		
		
		// Connect for removing a Layer Shell
		((Button) w.getW("lRemLShell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				try {
					ui.removeLayerShell( w.getL("lLayShells").getSelected() );
				} catch (Exception e) {			
					e.printStackTrace();
				}
				w.getL("lLayShells").removeData( );
				((Entry) w.getW("lLSType")).setText(" ");
				((Entry) w.getW("lLSLower")).setText(" ");
				((Entry) w.getW("lLSUpper")).setText(" ");
				w.getC("lShellSel").setChanged(true);
			}
		});	
		
		// Connect for adding a Layer
		((Button) w.getW("lAddLayer")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				currentLayer = ui.addLayer();				
				w.getL("lLayers").addData( currentLayer.type );
				w.getC("stLaySel").setChanged(true);
				w.getC("rLaySel").setChanged(true);
			}
		});
		
		// Connect for removing a Layer
		((Button) w.getW("lRemLayer")).connect(new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				try {
					ui.removeLayer( w.getL("lLayers").getSelected() );
				} catch (Exception e) {				
					e.printStackTrace();
				}				
				w.getL("lLayers").removeData();
				((Entry) w.getW("lLType")).setText("");
				w.getC("stLaySel").setChanged(true);
				w.getC("rLaySel").setChanged(true);
				
				
			}
		});
		
		// Modifying layers
		
		
		// Layer Shell type
		((Entry) w.getW("lLSType")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {						
				w.getL("lLayShells").removeData();		
				w.getL("lLayShells").addData(arg0.getText());	
				w.getL("lLayShells").getView().grabFocus();
				currentLayerShell.type = arg0.getText();
				Utils.setColor("lLSType", Utils.activeGreen, w);
				w.getC("rLaySel").setChanged(true);
			}
		});
		
		// Layer Shell lower
		((Entry) w.getW("lLSLower")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentLayerShell.lower = d;
					Utils.setColor("lLSLower", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("lLSLower", Utils.red, w);
				}
			}
		});
		
		// Layer Shell upper
		((Entry) w.getW("lLSUpper")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentLayerShell.upper = d;
					Utils.setColor("lLSUpper", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("lLSUpper", Utils.red, w);
				}
			}
		});
		
		// Layer type
		((Entry) w.getW("lLType")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {						
				w.getL("lLayers").removeData();		
				w.getL("lLayers").addData(arg0.getText());	
				w.getL("lLayers").getView().grabFocus();
				currentLayer.type = arg0.getText();			
				Utils.setColor("lLType", Utils.activeGreen, w);
			}
		});
	}
	
}
