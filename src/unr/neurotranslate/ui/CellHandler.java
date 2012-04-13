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

import unr.neurotranslate.ncs.Cell;
import unr.neurotranslate.ncs.Compartment;
import unr.neurotranslate.ncs.SpikeShape;
import unr.neurotranslate.ui.controller.UIControllerNCS;

public class CellHandler {
	// All array lists are for debugging
	public TreeSelection rs1, rs2, rs3;
	public String selectedText;
	public Cell currentCell;
	public Compartment currentCompartment;
	public SpikeShape currentSpikes;
	
	public CellHandler(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {
						
		w.getW("cellScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes				
				w.getL("ceCells").listToModel( ui.getCells() );
				w.getL("ceCompartments").listToModel( ui.getCompartments() );
				w.getL("ceSpikes").listToModel( ui.getSpikeShapes() );
				
				return false;
			}
		});
		
		setEntries(w, ui);
		
		modifyHandlers(w, ui);
		
	}

	
	public void setEntries(final WidgetReferences w, final UIControllerNCS ui) {
		
		// Entries are set depending on current cell selected
		TreeView cellView = w.getL("ceCells").getView();		
		
		// Connect for getting selected row in tree view		
		rs1 = cellView.getSelection();
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected cell
					selectedText = w.getL("ceCells").getModel().getValue(rs1.getSelected(), w.getL("ceCells").getHeader());

					// Get current cell based on cell
					try {
						currentCell = ui.getCellByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current
					((Entry) w.getW("ceCellType")).setText(currentCell.type);
					// TODO - set compartment name and all the entries
										
				}							
			}
		});
				
		// Entries are set depending on current compartments selected
		TreeView compView = w.getL("ceCompartments").getView();		
		
		// Connect for getting selected row in tree view		
		rs2 = compView.getSelection();
		rs2.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs2.getSelected() != null ) {
					
					// Get selected compartments
					selectedText = w.getL("ceCompartments").getModel().getValue(rs2.getSelected(), w.getL("ceCompartments").getHeader());

					// Get current compartments based on compartments
					try {
						currentCompartment = ui.getCompartmentByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current 
					// TODO - Set spike shape
					((Entry) w.getW("ceComType")).setText(currentCompartment.type);
					((Entry) w.getW("ceTMMean")).setText(currentCompartment.tauMembrane.mean.toString());
					((Entry) w.getW("ceTMStd")).setText(currentCompartment.tauMembrane.stdev.toString());
					((Entry) w.getW("ceRMMean")).setText(currentCompartment.rMembrane.mean.toString());
					((Entry) w.getW("ceRMStd")).setText(currentCompartment.rMembrane.stdev.toString());
					((Entry) w.getW("ceTMean")).setText(currentCompartment.threshold.mean.toString());
					((Entry) w.getW("ceTStd")).setText(currentCompartment.threshold.stdev.toString());
					((Entry) w.getW("ceLRMean")).setText(currentCompartment.leakReversal.mean.toString());
					((Entry) w.getW("ceLRStd")).setText(currentCompartment.leakReversal.stdev.toString());
					((Entry) w.getW("ceLCMean")).setText(currentCompartment.leakConductance.mean.toString());
					((Entry) w.getW("ceLCStd")).setText(currentCompartment.leakConductance.stdev.toString());
					((Entry) w.getW("ceRPMean")).setText(currentCompartment.vmRest.mean.toString());
					((Entry) w.getW("ceRPStd")).setText(currentCompartment.vmRest.stdev.toString());
				}							
			}
		});
		
		// Entries are set depending on spike selected
		TreeView spikeView = w.getL("ceSpikes").getView();		
		
		// Connect for getting selected row in tree view		
		rs3 = spikeView.getSelection();
		rs3.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs3.getSelected() != null ) {
					
					// Get selected spike
					selectedText = w.getL("ceSpikes").getModel().getValue(rs3.getSelected(), w.getL("ceSpikes").getHeader());

					// Get current spike shape based on selected spike
					try {
						currentSpikes = ui.getSpikeShapeByType(selectedText);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}									
				
					// Set everything else to current
					((Entry) w.getW("ceSpikeType")).setText(currentSpikes.type);					
					((Entry) w.getW("ceSpikeVol")).setText(currentSpikes.voltages.toString());
					
				}							
			}
		});
	}
	
	public void modifyHandlers(final WidgetReferences w, UIControllerNCS ui) throws FileNotFoundException {
	
		// Connect for adding a cell
		((Button) w.getW("ceAddCell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("ceCells").addData( "new cell");
				
			}
		});		
		
		// Connect for removing a cell
		((Button) w.getW("ceRemCell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				w.getL("ceCells").removeData( );
				
			}
		});	
		
		// Connect for adding a compartment
		((Button) w.getW("ceAddComp")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("ceCompartments").addData( "new compartment" );
			}
		});
		
		// Connect for removing a compartment
		((Button) w.getW("ceRemComp")).connect(new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("ceCompartments").removeData();
				
			}
		});
		
		// Connect for adding a Spike Shape
		((Button) w.getW("ceAddSpike")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("ceSpikes").addData( "new spike shape" );
				
			}
		});
		
		// Connect for removing a Spike Shape
		((Button) w.getW("ceRemSpike")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				w.getL("ceSpikes").removeData();
				
			}
		});
	}
}
