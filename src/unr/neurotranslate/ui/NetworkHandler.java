package unr.neurotranslate.ui;

import java.math.BigInteger;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.ComboBox;
import org.gnome.gtk.Entry;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.Entry.Activate;
import org.gnome.gtk.TreeSelection.Changed;
import org.morphml.networkml.schema.Input;
import org.morphml.networkml.schema.Population;
import org.morphml.networkml.schema.Projection;

import unr.neurotranslate.ui.controller.UIControllerNeuroML;

public class NetworkHandler {
	
	public String selectedText;	
	public TreeSelection rs1, rs2, rs3;
	public Population currentPopulation;
	public Projection currentProjection;
	public Input currentInput;
	
	public NetworkHandler( final WidgetReferences w, final UIControllerNeuroML ui ) {
		
		w.getW("networkScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
			
				w.getL("nPops").listToModel( ui.getPopulations() );
				w.getL("nProjs").listToModel( ui.getProjections() );
				w.getL("nInputs").listToModel( ui.getInputs() );
				w.getC("nCellSel").listToModel( ui.getCells() );
				w.getC("nCellSel").getView().setActive(0);
				
				return false;
			}
		});
		
		setEntries( w, ui );
		
		modifyHandler( w, ui );
		
	}
	
	private void setEntries(final WidgetReferences w, final UIControllerNeuroML ui) {
	
		// Entries are set depending on current population selected
		TreeView popView = w.getL("nPops").getView();		
		
		// Connect for getting selected row in tree view		
		rs1 = popView.getSelection();						
		rs1.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs1.getSelected() != null ) {
					
					// Get selected pop
					selectedText = w.getL("nPops").getModel().getValue(rs1.getSelected(), w.getL("nPops").getHeader());
					
					// Get pop based on selected pop 
					try {
						 currentPopulation = ui.getPopulationByName(selectedText);
					} catch (Exception e) {						
						e.printStackTrace();
					}									
														
					// Set everything else to current pop 						
					((Entry) w.getW("nPopSize")).setText( (currentPopulation.getPopLocation().getRandomArrangement().getPopulationSize()).toString() );					
					((Entry) w.getW("nCornerX")).setText( Double.toString(currentPopulation.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX()) );
					((Entry) w.getW("nCornerY")).setText( Double.toString(currentPopulation.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY()) );
					((Entry) w.getW("nCornerZ")).setText( Double.toString(currentPopulation.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getZ()) );
					((Entry) w.getW("nSizeW")).setText( Double.toString(currentPopulation.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getWidth()) );
					((Entry) w.getW("nSizeH")).setText( Double.toString(currentPopulation.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getHeight()) );
				}							
			}
		});
		
		// Entries are set depending on current projection selected
		TreeView projView = w.getL("nProjs").getView();		
		
		// Connect for getting selected row in tree view		
		rs2 = projView.getSelection();						
		rs2.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs2.getSelected() != null ) {
					
					// Get selected projection
					selectedText = w.getL("nProjs").getModel().getValue(rs2.getSelected(), w.getL("nPops").getHeader());
					
					// Get projection based on selected projection
					try {
						 currentProjection = ui.getProjectionByName(selectedText);
					} catch (Exception e) {						
						e.printStackTrace();
					}									
				
					// Set everything else to current projection 						
					//((Entry) w.getW("nProjectUnits")).setText( currentProjection.get );					
				}							
			}
		});			
		
		// Entries are set depending on current input selected
		TreeView inputView = w.getL("nInputs").getView();		
		
		// Connect for getting selected row in tree view		
		rs3 = inputView.getSelection();						
		rs3.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
			
				// Get selected string
				if( rs3.getSelected() != null ) {
					
					// Get selected input
					selectedText = w.getL("nInputs").getModel().getValue(rs3.getSelected(), w.getL("nInputs").getHeader());
					
					// Get input based on selected input
					try {
						 currentInput = ui.getInputByName(selectedText);
					} catch (Exception e) {						
						e.printStackTrace();
					}									
				
					// Set everything else to current input 						
					((Entry) w.getW("nPulseDelay")).setText( Double.toString(currentInput.getPulseInput().getDelay()) );
					((Entry) w.getW("nPulseDuration")).setText( Double.toString(currentInput.getPulseInput().getDuration()) );
					((Entry) w.getW("nPulseAmp")).setText( Double.toString(currentInput.getPulseInput().getAmplitude()) );
					
				}							
			}
		});
	}
	
	private void modifyHandler(WidgetReferences w, UIControllerNeuroML ui) {
	
		// Adding populations
		((Button)w.getW("nAddPop")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
	
				
			}
		});
		
		// Removing populations
		((Button)w.getW("nRemPop")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				
			}
		});
		
		// Adding projections
		((Button)w.getW("nAddProj")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
	
				
			}
		});
		
		// Removing projections
		((Button)w.getW("nRemProj")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				
			}
		});
		
		// Adding Inputs
		((Button)w.getW("nAddInput")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
	
				
			}
		});
		
		// Removing Inputs
		((Button)w.getW("nRemInput")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				
			}
		});
		
		// Population size
		((Entry) w.getW("nPopSize")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});

		// Corner
			// X			
			((Entry) w.getW("nCornerX")).connect(new Activate() {
			
				@Override
				public void onActivate(Entry arg0) {
					
					
				}
			});
			
			// Y
			((Entry) w.getW("nCornerY")).connect(new Activate() {
				
				@Override
				public void onActivate(Entry arg0) {
					
					
				}
			});
			
			// Z
			((Entry) w.getW("nCornerZ")).connect(new Activate() {
				
				@Override
				public void onActivate(Entry arg0) {
					
					
				}
			});
			
		
		// Size 
			// W
			((Entry) w.getW("nSizeW")).connect(new Activate() {
				
				@Override
				public void onActivate(Entry arg0) {
					
					
				}
			});
			
			// H
			((Entry) w.getW("nSizeH")).connect(new Activate() {
				
				@Override
				public void onActivate(Entry arg0) {
					
					
				}
			});
			
		// Projection Units
		((Entry) w.getW("nProjectUnits")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Probability
		((Entry) w.getW("nProb")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
				
		// Internal Delay
		((Entry) w.getW("nIntDelay")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Wait
		((Entry) w.getW("nWait")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Threshold
		((Entry) w.getW("nThresh")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Input Units
		((Entry) w.getW("nInputUnits")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});
		
		// Pulse Input
			// Delay
			((Entry) w.getW("nPulseDelay")).connect(new Activate() {
				
				@Override
				public void onActivate(Entry arg0) {
					
					
				}
			});
		
			// Delay
			((Entry) w.getW("nPulseDuration")).connect(new Activate() {
				
				@Override
				public void onActivate(Entry arg0) {
					
					
				}
			});
		
			// Amplitude
			((Entry) w.getW("nPulseAmp")).connect(new Activate() {
				
				@Override
				public void onActivate(Entry arg0) {
					
					
				}
			});
		
		// Site Patterns
		((Entry) w.getW("nPattern")).connect(new Activate() {
			
			@Override
			public void onActivate(Entry arg0) {
				
				
			}
		});		
	}
}
