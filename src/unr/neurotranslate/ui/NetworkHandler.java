package unr.neurotranslate.ui;

import java.util.ArrayList;

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
import org.morphml.channelml.schema.SynapseType;
import org.morphml.networkml.schema.ConnectivityPattern;
import org.morphml.networkml.schema.GlobalSynapticProperties;
import org.morphml.networkml.schema.Input;
import org.morphml.networkml.schema.InputSitePattern;
import org.morphml.networkml.schema.InputTarget;
import org.morphml.networkml.schema.Inputs;
import org.morphml.networkml.schema.Population;
import org.morphml.networkml.schema.Projection;
import org.morphml.networkml.schema.Projections;
import org.morphml.networkml.schema.PulseInput;
import org.morphml.networkml.schema.ConnectivityPattern.FixedProbability;
import org.morphml.networkml.schema.InputSitePattern.PercentageCells;

import unr.neurotranslate.ui.controller.UIControllerNeuroML;

public class NetworkHandler {
	
	public String selectedText;	
	public TreeSelection rs1, rs2, rs3, rs4;
	public Population currentPopulation;
	public Projection currentProjection;
	public Projections currentProjectionClass;
	public Input currentInput;
	public Inputs currentInputClass; 	
	public InputSitePattern currentPattern;
	public SynapseType currentSynapse;
	
	
	public NetworkHandler( final WidgetReferences w, final UIControllerNeuroML ui ) {
		
		w.getW("networkScroll").connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
			
				w.getL("nPops").listToModel( ui.getPopulations() );
				w.getL("nProjs").listToModel( ui.getProjections() );
				w.getL("nInputs").listToModel( ui.getInputs() );
				
				if( w.getC("nCellSel").getChanged() ) {
					w.getC("nCellSel").listToModel( ui.getCells() );
					w.getC("nCellSel").setChanged(false);					
				}
											
				if( w.getC("nSourceSel").getChanged()) {
					w.getC("nSourceSel").listToModel( ui.getPopulations() );
					w.getC("nSourceSel").setChanged(false);
				}
				
				if( w.getC("nTargetSel").getChanged() ) {					
					w.getC("nTargetSel").listToModel( ui.getPopulations() );
					w.getC("nTargetSel").setChanged(false);
				}

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
					if(currentPopulation.getPopLocation() != null) {
						((Entry) w.getW("nPopSize")).setText( (currentPopulation.getPopLocation().getRandomArrangement().getPopulationSize()).toString() );
						((Entry) w.getW("nCornerX")).setText( Double.toString(currentPopulation.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX()) );
						((Entry) w.getW("nCornerY")).setText( Double.toString(currentPopulation.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY()) );
						((Entry) w.getW("nCornerZ")).setText( Double.toString(currentPopulation.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getZ()) );
						((Entry) w.getW("nSizeW")).setText( Double.toString(currentPopulation.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getWidth()) );
						((Entry) w.getW("nSizeH")).setText( Double.toString(currentPopulation.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getHeight()) );	
					}
					
					else {
						((Entry) w.getW("nPopSize")).setText("");
						((Entry) w.getW("nCornerX")).setText ("");
						((Entry) w.getW("nCornerY")).setText ("");
						((Entry) w.getW("nCornerZ")).setText ("");
						((Entry) w.getW("nSizeW")).setText ("");
						((Entry) w.getW("nSizeH")).setText ("");
					}
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
				
					currentProjectionClass = ui.getProjectionsClass();
					
					// Set everything else to current projection					
					ArrayList<String> s = new ArrayList<String>();
					for(GlobalSynapticProperties t: currentProjection.getSynapseProps() ) {
						s.add(t.getSynapseType());
					}					
					w.getC("nSynSel").listToModel(s);
					
					if( currentProjection.getConnectivityPattern() != null ) {
						((Entry) w.getW("nProb")).setText(currentProjection.getConnectivityPattern().getFixedProbability().getProbability().toString());
						
					}
					else {
						((Entry) w.getW("nProb")).setText("");
					}
					
					if( currentProjectionClass.getUnits() != null ) {
						((Entry) w.getW("nProjectUnits")).setText( currentProjectionClass.getUnits().toString() );	
					}
					else {
						((Entry) w.getW("nProjectUnits")).setText("");
					}
				
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
				
					 currentInputClass = ui.getInputsClass();
					
					// Set everything else to current input 
					if( currentInput.getPulseInput() != null ) {
						((Entry) w.getW("nPulseDelay")).setText( Double.toString(currentInput.getPulseInput().getDelay()) );
						((Entry) w.getW("nPulseDuration")).setText( Double.toString(currentInput.getPulseInput().getDuration()) );
						((Entry) w.getW("nPulseAmp")).setText( Double.toString(currentInput.getPulseInput().getAmplitude()) );
						((Entry) w.getW("nPattern")).setText( currentInput.getTarget().getSitePattern().getPercentageCells().getPercentage().toString() );	
					}
					
					else {
						((Entry) w.getW("nPulseDelay")).setText( "" );
						((Entry) w.getW("nPulseDuration")).setText( "" );
						((Entry) w.getW("nPulseAmp")).setText( "" );
						((Entry) w.getW("nPattern")).setText( "" );
					}
					
					if( currentInputClass.getUnits() != null ) {
						((Entry) w.getW("nInputUnits")).setText( currentInputClass.getUnits().toString() );	
					}
					
					else {
						((Entry) w.getW("nInputUnits")).setText("");
					}
				}							
			}
		});			
	}
	
	private void modifyHandler(final WidgetReferences w, final UIControllerNeuroML ui) {
	
		// Adding populations
		((Button)w.getW("nAddPop")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				
				w.getC("nTargetSel").setChanged(true);	
				w.getC("nSourceSel").setChanged(true);	
			}
		});
		
		// Removing populations
		((Button)w.getW("nRemPop")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				w.getC("nTargetSel").setChanged(true);	
				w.getC("nSourceSel").setChanged(true);				
			}
		});
		
		// Adding projections
		((Button)w.getW("nAddProj")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
	
				currentProjection = ui.addProjection();
				currentProjection.setConnectivityPattern(new ConnectivityPattern());
				currentProjection.getConnectivityPattern().setFixedProbability(new FixedProbability());
				currentProjection.getConnectivityPattern().getFixedProbability().setProbability(0.0);
				w.getL("nProjs").addData(currentProjection.getName());
				
			}
		});
		
		// Removing projections
		((Button)w.getW("nRemProj")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				try {
					ui.removeProjection(w.getL("nProjs").getSelected());
				} catch (Exception e) {				
					///e.printStackTrace();
				}
				
				w.getL("nProjs").removeData();
				((Entry) w.getW("nProjectUnits")).setText("");
				((Entry) w.getW("nProb")).setText("");
			}
		});
		
		// Adding Inputs
		((Button)w.getW("nAddInput")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
	
				currentInput = ui.addInput();				
				w.getL("nInputs").addData(currentInput.getName());
				currentInput.setPulseInput( new PulseInput() );
				currentInput.getPulseInput().setAmplitude(0);
				currentInput.getPulseInput().setDelay(0);
				currentInput.getPulseInput().setDuration(0);
				
				currentInput.setTarget( new InputTarget() );
				currentInput.getTarget().setSitePattern( new InputSitePattern() );
				currentInput.getTarget().getSitePattern().setPercentageCells( new PercentageCells() );
				currentInput.getTarget().getSitePattern().getPercentageCells().setPercentage(0.0);
				
			}
		});
		
		// Removing Inputs
		((Button)w.getW("nRemInput")).connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
		
				try {
					ui.removeInput(w.getL("nInputs").getSelected());
				} catch (Exception e) {				
					//e.printStackTrace();
				}
				
				w.getL("nInputs").removeData();
				((Entry) w.getW("nInputUnits")).setText( "" );
				((Entry) w.getW("nPulseDelay")).setText( "" );
				((Entry) w.getW("nPulseDuration")).setText( "" );
				((Entry) w.getW("nPulseAmp")).setText( "" );
				((Entry) w.getW("nPattern")).setText( "" );
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
