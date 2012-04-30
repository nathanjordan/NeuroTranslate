package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.ComboBox;
import org.gnome.gtk.Entry;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.Entry.Activate;
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
					ArrayList<String> c = new ArrayList<String>();
					if(currentCell.compartmentNames != null) {		// TODO - remove if not needed
						for( String s: currentCell.compartmentNames )
							c.add(s);			
						if(c.size() >0 ) {
							w.getC("ceComName").listToModel(c);		
						}
						
					}
					
					
					((Entry)w.getW("ceComLab")).setText("");
					((Entry)w.getW("ceComX")).setText("");
					((Entry)w.getW("ceComY")).setText("");
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
						e.printStackTrace();
					}									
				
					// Set everything else to current 			
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
		
		ComboBox comView = w.getC("ceComName").getView();
		comView.connect( new ComboBox.Changed() {
			
			@Override
			public void onChanged(ComboBox arg0) {
				
				if(currentCell.compartmentNames.size() > 0 ) {
					((Entry)w.getW("ceComLab")).setText(currentCell.compartmentLabels.get(0).toString());		
				}
				else {
					((Entry)w.getW("ceComLab")).setText("");	
				}
				if(currentCell.xList.size() > 0 ) {
					((Entry)w.getW("ceComX")).setText(currentCell.xList.get(0).toString());	
				}
				else {
					((Entry)w.getW("ceComX")).setText("");
				}
				if(currentCell.yList.size() > 0 ) {
					((Entry)w.getW("ceComY")).setText(currentCell.yList.get(0).toString());		
				}
				else {
					((Entry)w.getW("ceComY")).setText("");
				}
				
			}
		});
	}
	
	public void modifyHandlers(final WidgetReferences w, final UIControllerNCS ui) throws FileNotFoundException {
	
		// Connect for adding a cell
		((Button) w.getW("ceAddCell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				currentCell = ui.addCell();
				w.getL("ceCells").addData( currentCell.type );
				
				w.getC("stCellSel").setChanged(true);
				w.getC("rCellSel").setChanged(true);				
			}
		});		
		
		// Connect for removing a cell
		((Button) w.getW("ceRemCell")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				try {
					ui.removeCell( w.getL("ceCells").getSelected() );
				} catch (Exception e) {
					e.printStackTrace();
				}
				w.getL("ceCells").removeData( );
				w.getC("stCellSel").setChanged(true);
				w.getC("rCellSel").setChanged(true);
				((Entry) w.getW("ceCellType")).setText("");			
			}
		});	
		
		// Connect for adding a compartment
		((Button) w.getW("ceAddComp")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				currentCompartment = ui.addCompartment();						
				w.getL("ceCompartments").addData( currentCompartment.type );
				w.getC("stCompSel").setChanged(true);
				w.getC("rCompSel").setChanged(true);
			}
		});
		
		// Connect for removing a compartment
		((Button) w.getW("ceRemComp")).connect(new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				try {
					ui.removeCompartment( w.getL("ceCompartments").getSelected() );
				} catch (Exception e) {		
					e.printStackTrace();
				}				
				w.getL("ceCompartments").removeData();
				w.getC("stCompSel").setChanged(true);
				w.getC("rCompSel").setChanged(true);
				
				((Entry) w.getW("ceComType")).setText("");
				((Entry) w.getW("ceTMMean")).setText("");
				((Entry) w.getW("ceTMStd")).setText("");
				((Entry) w.getW("ceRMMean")).setText("");
				((Entry) w.getW("ceRMStd")).setText("");
				((Entry) w.getW("ceTMean")).setText("");
				((Entry) w.getW("ceTStd")).setText("");
				((Entry) w.getW("ceLRMean")).setText("");
				((Entry) w.getW("ceLRStd")).setText("");
				((Entry) w.getW("ceLCMean")).setText("");
				((Entry) w.getW("ceLCStd")).setText("");
				((Entry) w.getW("ceRPMean")).setText("");
				((Entry) w.getW("ceRPStd")).setText("");
			}
		});
		
		// Connect for adding a Spike Shape
		((Button) w.getW("ceAddSpike")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				currentSpikes = ui.addSpikeShape();
				w.getL("ceSpikes").addData( currentSpikes.type );
				
			}
		});
		
		// Connect for removing a Spike Shape
		((Button) w.getW("ceRemSpike")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				try {
					ui.removeSpikeShape( w.getL("ceSpikes").getSelected() );
				} catch (Exception e) {		
					e.printStackTrace();
				}
				w.getL("ceSpikes").removeData();
				((Entry) w.getW("ceSpikeType")).setText("");
				((Entry) w.getW("ceSpikeVol")).setText("");
			}
		});
		
		// Cell Type
		((Entry) w.getW("ceCellType")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {						
				w.getL("ceCells").removeData();		
				w.getL("ceCells").addData(arg0.getText());	
				w.getL("ceCells").getView().grabFocus();
				currentCell.type = arg0.getText();	
				Utils.setColor("ceCellType", Utils.activeGreen, w);
				w.getC("rCellSel").setChanged(true);
			}
		});
		
		// Soma Type
		((Entry) w.getW("ceComType")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {						
				w.getL("ceCompartments").removeData();		
				w.getL("ceCompartments").addData(arg0.getText());	
				w.getL("ceCompartments").getView().grabFocus();
				currentCompartment.type = arg0.getText();	
				Utils.setColor("ceComType", Utils.activeGreen, w);
				w.getC("rCompSel").setChanged(true);
			}
		});
		
		// Tau Membrane
		((Entry) w.getW("ceTMMean")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentCompartment.tauMembrane.mean = d;
					Utils.setColor("ceTMMean", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					Utils.setColor("ceTMMean", Utils.red, w);
					arg0.setText("");
				}
			}
		});
		
		((Entry) w.getW("ceTMStd")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentCompartment.tauMembrane.stdev = d;
					Utils.setColor("ceTMStd", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("ceTMStd", Utils.red, w);
				}
			}
		});

		// R Membrane
		((Entry) w.getW("ceRMMean")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentCompartment.rMembrane.mean = d;
					Utils.setColor("ceRMMean", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("ceRMMean", Utils.red, w);
				}
			}
		});
		
		((Entry) w.getW("ceRMStd")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentCompartment.rMembrane.stdev = d;
					Utils.setColor("ceRMStd", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("ceRMStd", Utils.red, w);
				}
			}
		});
		
		// Threshold
		((Entry) w.getW("ceTMean")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentCompartment.threshold.mean = d;
					Utils.setColor("ceTMean", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("ceTMean", Utils.red, w);
				}
			}
		});
		
		((Entry) w.getW("ceTStd")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentCompartment.threshold.stdev = d;
					Utils.setColor("ceTStd", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("ceTStd", Utils.red, w);
				}
			}
		});
		
		// Leak Reversal
		((Entry) w.getW("ceLRStd")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentCompartment.leakReversal.mean = d;
					Utils.setColor("ceLRStd", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("ceLRStd", Utils.red, w);
				}
			}
		});
		
		((Entry) w.getW("ceLRStd")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentCompartment.leakReversal.stdev = d;
					Utils.setColor("ceLRStd", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("ceLRStd", Utils.red, w);
				}
			}
		});
		
		// Leak Conductance
		((Entry) w.getW("ceLCMean")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentCompartment.leakConductance.mean = d;
					Utils.setColor("ceLCMean", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("ceLCMean", Utils.red, w);
				}
			}
		});
		
		((Entry) w.getW("ceLCStd")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentCompartment.leakConductance.stdev = d;
					Utils.setColor("ceLCStd", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("ceLCStd", Utils.red, w);
				}
			}
		});
		
		// Resting Potential
		((Entry) w.getW("ceRPMean")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentCompartment.rMembrane.mean = d;
					Utils.setColor("ceRPMean", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("ceRPMean", Utils.red, w);
				}
			}
		});
		
		((Entry) w.getW("ceRPStd")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				try {
					double d = Double.parseDouble(arg0.getText());
					currentCompartment.rMembrane.stdev = d;
					Utils.setColor("ceRPStd", Utils.activeGreen, w);
				} catch( NumberFormatException nfe ) {
					arg0.setText("");
					Utils.setColor("ceRPStdr", Utils.red, w);
				}
			}
		});
		
		// Spike Shape Type
		((Entry) w.getW("ceSpikeType")).connect( new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {						
				w.getL("ceSpikes").removeData();		
				w.getL("ceSpikes").addData(arg0.getText());	
				w.getL("ceSpikes").getView().grabFocus();
				currentSpikes.type = arg0.getText();	
				Utils.setColor("ceSpikeType", Utils.activeGreen, w);
			}
		});
		
		// TODO - Spike Shape Voltages
	}
}
