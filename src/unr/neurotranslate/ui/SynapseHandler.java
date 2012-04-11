package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Label;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Button.Clicked;

import unr.neurotranslate.ncs.SynFacilDepress;
import unr.neurotranslate.ncs.SynLearning;
import unr.neurotranslate.ncs.Synapse;

public class SynapseHandler {
	// All array lists are for debugging
	public ArrayList<String> sSynap;
	public ArrayList<String> sfd;
	public ArrayList<String> ll;
	
	public ListEntity synapses;
	public ListEntity SFDLabels;
	public ListEntity LearnLabels;	
	public Entry syType;
	public Entry sySYN;
	public Entry syHStart;
	public Entry syHEnd;
	public Entry syAbMean;
	public Entry syAbStd;
	public Entry syMCMean;
	public Entry syMCStd;
	public Entry syDMin;
	public Entry syDMax;
	public Entry sySRMean;
	public Entry sySRStd;
	public Entry sySFDType;
	public Entry sySFD;
	public Entry sySFDFTMean;
	public Entry sySFDFTStd;
	public Entry sySFDDTMean;
	public Entry sySFDDTStd;
	public Entry syLLType;
	public Entry syLLLearning;
	public Entry syLLFTMean;
	public Entry syLLFTStd;
	public Entry syLLDTMean;
	public Entry syLLDTStd;
	public Entry syLLNebWinMean;
	public Entry syLLNebWinStd;
	public Entry syLLPosWinMean;
	public Entry ssLLPosWinStd;
	public Entry ssLLPosPeakMean;
	public Entry ssLLPosPeakStd;
	public Entry ssLLNegMean;
	public Entry ssLLNegStd;
	public Synapse currentSynapse;	
	public SynFacilDepress currentSFD;
	public SynLearning currentLearn;
	
	public SynapseHandler() throws FileNotFoundException {
		
		ScrolledWindow c = (ScrolledWindow) GladeParseUtil.grabWidget("scrolledwindow5", "window1");
		
		c.connect(new Widget.ExposeEvent() {
			
			@Override
			public boolean onExposeEvent(Widget arg0, EventExpose arg1) {
				
				// fill out all entries/lists/combo boxes
				
				
				
				return false;
			}
		});
		
		// Debugging model
		sSynap = new ArrayList<String>();
		sfd = new ArrayList<String>();
		ll = new ArrayList<String>();
		
		// Entries
		syType = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		sySYN = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		syHStart = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		syHEnd = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		syAbMean = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		syAbStd = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		syMCMean = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		syMCStd = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		syDMin = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		syDMax = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		sySRMean = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		sySRStd = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		sySFDType = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		sySFD = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		sySFDFTMean = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		sySFDFTStd = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		sySFDDTMean = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		sySFDDTStd = (Entry) GladeParseUtil.grabWidget("entry", "window1");
		syLLType = (Entry) GladeParseUtil.grabWidget("entry54", "window1");
		syLLLearning = (Entry) GladeParseUtil.grabWidget("entry66", "window1");
		syLLFTMean = (Entry) GladeParseUtil.grabWidget("entry67", "window1");
		syLLFTStd = (Entry) GladeParseUtil.grabWidget("entry68", "window1");
		syLLDTMean = (Entry) GladeParseUtil.grabWidget("entry69", "window1");
		syLLDTStd = (Entry) GladeParseUtil.grabWidget("entry70", "window1");
		syLLNebWinMean = (Entry) GladeParseUtil.grabWidget("entry71", "window1");
		syLLNebWinStd = (Entry) GladeParseUtil.grabWidget("entry72", "window1");
		syLLPosWinMean = (Entry) GladeParseUtil.grabWidget("entry73", "window1");
		ssLLPosWinStd = (Entry) GladeParseUtil.grabWidget("entry74", "window1");
		ssLLPosPeakMean = (Entry) GladeParseUtil.grabWidget("entry75", "window1");
		ssLLPosPeakStd = (Entry) GladeParseUtil.grabWidget("entry76", "window1");
		ssLLNegMean = (Entry) GladeParseUtil.grabWidget("entry77", "window1");
		ssLLNegStd = (Entry) GladeParseUtil.grabWidget("entry78", "window1");
		
		// Lists
		synapses = new ListEntity( "SynapseList", "window1" );
		SFDLabels = new ListEntity( "SFDLabels", "window1" );
		LearnLabels = new ListEntity( "LearnLabels", "window1" );
		
		setLists();
		
		setEntries();
		
		modifyHandlers();
		
	}
	
	public void setLists() throws FileNotFoundException {
	
		
				
	}
	
	public void setEntries() {
		
	}
	
	public void modifyHandlers() throws FileNotFoundException {
	
		//Buttons for add/removing
		Button addSynapse = (Button) GladeParseUtil.grabWidget( "sSynapseAdd", "window1" );
		Button remSynapse = (Button) GladeParseUtil.grabWidget( "sSynapseRem", "window1" );
		Button addSFD = (Button) GladeParseUtil.grabWidget( "sSFDAdd", "window1" );
		Button remSFD = (Button) GladeParseUtil.grabWidget( "sSFDRem", "window1" );
		Button addLearn = (Button) GladeParseUtil.grabWidget( "sLearnAdd", "window1" );
		Button remLearn = (Button) GladeParseUtil.grabWidget( "sLearnRem", "window1" );
		
		// Connect for adding a cell
		addSynapse.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				synapses.addData( "new synapse");
				
			}
		});		
		
		// Connect for removing a cell
		remSynapse.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				synapses.removeData( );
				
			}
		});	
		
		// Connect for adding a compartment
		addSFD.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				SFDLabels.addData( "new sfd label" );
			}
		});
		
		// Connect for removing a compartment
		remSFD.connect(new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				SFDLabels.removeData();
				
			}
		});
		
		// Connect for adding a Spike Shape
		addLearn.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				LearnLabels.addData( "new learn label" );
				
			}
		});
		
		// Connect for removing a Spike Shape
		remLearn.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				
				LearnLabels.removeData();
				
			}
		});
	}

}
