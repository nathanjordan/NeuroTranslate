package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

// This class contains references to every widget in glade. 
public class WidgetReferences {

	// Use a hash map to hold the widget references	
	private HashMap<String, ListEntity> l1;		// l1 holds all the ListEntity objects 
	private HashMap<String, ComboEntity> l2;	// l2 holds all the ComboEntity objects
	private HashMap<String, Widget> l3;			// l3 holds all the remaining widgets
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Brain Tab
	//////////////////////////////////////////////////////////////////////////////////////////
	

	
	public WidgetReferences() throws FileNotFoundException {
		
		// Hash Map references to List widgets, Combobox widgets, and all other widgets (entries/buttons)
		l1 = new HashMap<String, ListEntity>();
		l2 = new HashMap<String, ComboEntity>();
		l3 = new HashMap<String, Widget>();	
		
		//////////////////////////////////////////////////////////////////////////////////////////
		////    Brain Tab
		//////////////////////////////////////////////////////////////////////////////////////////
		l3.put( "brainScroll", GladeParseUtil.grabWidget("scrolledwindow1", "window1") );
		l1.put( "bColTypes", new ListEntity( "bColTypes", "window1" ) );
		l1.put( "bStimInjects", new ListEntity( "bStimInjects", "window1" ) );		
		l1.put( "bReports", new ListEntity( "bReports", "window1" ) );
		l1.put( "bConnectionss", new ListEntity( "bConnections", "window1" ) );		
		l3.put("bType", GladeParseUtil.grabWidget( "brainType", "window1" ) );
		l3.put("bJob", GladeParseUtil.grabWidget( "brainJob", "window1" ) );
		l3.put("bFSV", GladeParseUtil.grabWidget( "brainFSV", "window1" ) );
		l3.put("bDur", GladeParseUtil.grabWidget( "brainDuration", "window1" ) );
		l3.put("bSeed", GladeParseUtil.grabWidget( "brainSeed", "window1" ) );
		l3.put("bModColumns",  GladeParseUtil.grabWidget( "bModColumns", "window1" ) );
		l3.put("bModStimulus",  GladeParseUtil.grabWidget( "bModStimulus", "window1" ) );
		l3.put("bModReports",  GladeParseUtil.grabWidget( "bModReports", "window1" ) );
		l3.put("bModConnection",  GladeParseUtil.grabWidget( "bModConnections", "window1" ) );
		l3.put("popup",  GladeParseUtil.grabWidget( "window2", "window2" ) );				
		
		//////////////////////////////////////////////////////////////////////////////////////////
		////    Columns Tab
		//////////////////////////////////////////////////////////////////////////////////////////
		l3.put( "columnScroll", GladeParseUtil.grabWidget( "scrolledwindow6", "window1" ) );
		l1.put( "coColShells", new ListEntity( "coColShells", "window1" ) );
		l1.put( "coColumns", new ListEntity( "coColumns", "window1" ) );
		l1.put( "coLayers", new ListEntity( "coLayers", "window1" ) );
		l2.put( "coColShellSel", new ComboEntity( "combobox2", "window1" ) );
		l3.put( "coCSType", GladeParseUtil.grabWidget( "csType", "window1" ) );
		l3.put( "coWidth", GladeParseUtil.grabWidget( "csWidth", "window1" ) );
		l3.put( "coHeight", GladeParseUtil.grabWidget( "csHeight", "window1" ) );
		l3.put( "coLocX", GladeParseUtil.grabWidget( "csLocX", "window1" ) );
		l3.put( "coLocY", GladeParseUtil.grabWidget( "csLocY", "window1" ) );
		l3.put( "coCType", GladeParseUtil.grabWidget( "entry12", "window1" ) );
		l3.put("coAddCShell", GladeParseUtil.grabWidget( "cShellAdd", "window1" ) );
		l3.put("coRemCShell", GladeParseUtil.grabWidget( "cShellRem", "window1" ) );
		l3.put("coAddColumn", GladeParseUtil.grabWidget( "cColAdd", "window1" ) );
		l3.put("coRemColumn", GladeParseUtil.grabWidget( "cColRem", "window1" ) );
		
		//////////////////////////////////////////////////////////////////////////////////////////
		////    Layers Tab
		//////////////////////////////////////////////////////////////////////////////////////////
		l3.put( "layerScroll", GladeParseUtil.grabWidget( "scrolledwindow2", "window1" ) );
		l1.put( "lLayShells", new ListEntity( "lLayerShells", "window1" ) );
		l1.put( "lLayers", new ListEntity( "lLayers", "window1" ) );
		l2.put( "lShellSel", new ComboEntity( "combobox1", "window1" ) );		
		l3.put( "lLSType", GladeParseUtil.grabWidget( "entry15", "window1" ) );
		l3.put( "lLSLower", GladeParseUtil.grabWidget( "entry14", "window1" ) );
		l3.put( "lLSUpper", GladeParseUtil.grabWidget( "entry13", "window1" ) );
		l3.put( "lLType", GladeParseUtil.grabWidget( "entry17", "window1" ) );	
		l3.put("lAddLShell", GladeParseUtil.grabWidget( "lShellAdd", "window1" ) );
		l3.put("lAddLayer", GladeParseUtil.grabWidget( "lLayAdd", "window1" ) );
		l3.put("lRemLShell", GladeParseUtil.grabWidget( "lShellRem", "window1" ) );
		l3.put("lRemLayer", GladeParseUtil.grabWidget( "lLayRem", "window1" ) );
		
		//////////////////////////////////////////////////////////////////////////////////////////
		////    Cells Tab
		//////////////////////////////////////////////////////////////////////////////////////////
		l3.put( "cellScroll", GladeParseUtil.grabWidget( "scrolledwindow4", "window1" ) );
		l1.put( "ceCells", new ListEntity( "cCells", "window1" ) );
		l1.put( "ceCompartments", new ListEntity( "Compartments", "window1" ) );
		l1.put( "ceSpikes", new ListEntity( "SpikeShapes", "window1" ) );
		l2.put( "ceComName", new ComboEntity( "combobox10", "window1" ) );
		l2.put( "ceSpikeSel", new ComboEntity( "combobox3", "window1" ) );
		l2.put( "ceSFDSel", new ComboEntity( "combobox11", "window1" ) );
		l2.put( "ceLearnSel", new ComboEntity( "combobox12", "window1" ) );
		l2.put( "celStimSel", new ComboEntity( "combobox4", "window1" ) );
		l2.put( "ceColSel", new ComboEntity( "combobox5", "window1" ) );
		l2.put( "ceLaySel", new ComboEntity( "combobox6", "window1" ) );
		l2.put( "ceCellSel", new ComboEntity( "combobox7", "window1" ) );
		l2.put( "ceCompSel", new ComboEntity( "combobox8", "window1" ) );
		l3.put( "ceCellType", GladeParseUtil.grabWidget("entry30", "window1") );
		l3.put( "ceComLab", GladeParseUtil.grabWidget("entry28", "window1") );
		l3.put( "ceComX", GladeParseUtil.grabWidget("entry29", "window1") );
		l3.put( "ceComY", GladeParseUtil.grabWidget("entry32", "window1") );
		l3.put( "ceComType", GladeParseUtil.grabWidget("entry31", "window1") );
		l3.put( "ceTMMean", GladeParseUtil.grabWidget("entry64", "window1") );
		l3.put( "ceTMStd", GladeParseUtil.grabWidget("entry65", "window1") );
		l3.put( "ceRMMean", GladeParseUtil.grabWidget("entry62", "window1") );
		l3.put( "ceRMStd", GladeParseUtil.grabWidget("entry63", "window1") );
		l3.put( "ceTMean", GladeParseUtil.grabWidget("entry60", "window1") );
		l3.put( "ceTStd", GladeParseUtil.grabWidget("entry61", "window1") );
		l3.put( "ceLRMean", GladeParseUtil.grabWidget("entry58", "window1") );
		l3.put( "ceLRStd", GladeParseUtil.grabWidget("entry59", "window1") );
		l3.put( "ceLCMean", GladeParseUtil.grabWidget("entry56", "window1") );
		l3.put( "ceLCStd", GladeParseUtil.grabWidget("entry57", "window1") );
		l3.put( "ceRPMean", GladeParseUtil.grabWidget("entry48", "window1") );
		l3.put( "ceRPStd", GladeParseUtil.grabWidget("entry49", "window1") );
		l3.put( "ceSpikeType", GladeParseUtil.grabWidget("entry40", "window1") );
		l3.put( "ceSpikeVol", GladeParseUtil.grabWidget("entry33", "window1") );
		l3.put("ceAddCell", GladeParseUtil.grabWidget( "cCellAdd", "window1" ) );
		l3.put("ceRemCell", GladeParseUtil.grabWidget( "cCellRem", "window1" ) );
		l3.put("ceAddComp", GladeParseUtil.grabWidget( "cCompAdd", "window1" ) );
		l3.put("ceRemComp", GladeParseUtil.grabWidget( "cCompRem", "window1" ) );
		l3.put("ceAddSpike", GladeParseUtil.grabWidget( "cSpikeAdd", "window1" ) );
		l3.put("ceRemSpike", GladeParseUtil.grabWidget( "cSpikeRem", "window1" ) );		
		
		//////////////////////////////////////////////////////////////////////////////////////////
		////    Connections Tab
		//////////////////////////////////////////////////////////////////////////////////////////
		l3.put( "connectionScroll", GladeParseUtil.grabWidget( "scrolledwindow11", "window1" ) );
		
		
		//////////////////////////////////////////////////////////////////////////////////////////
		////    Synapses Tab
		//////////////////////////////////////////////////////////////////////////////////////////
		l3.put( "synapseScroll", GladeParseUtil.grabWidget( "scrolledwindow5", "window1" ) );
		l1.put( "sySynapses", new ListEntity( "SynapseList", "window1" ) );
		l1.put( "sySFDLabels", new ListEntity( "SFDLabels", "window1" ) );
		l1.put( "syLearnLabels", new ListEntity( "LearnLabels", "window1" ) );
		l3.put( "syType", GladeParseUtil.grabWidget("entry43", "window1") );
		l3.put( "sySYN", GladeParseUtil.grabWidget("entry41", "window1") );
		l3.put( "syHStart", GladeParseUtil.grabWidget("entry42", "window1") );
		l3.put( "syHEnd", GladeParseUtil.grabWidget("entry45", "window1") );
		l3.put( "syAbMean", GladeParseUtil.grabWidget("entry34", "window1") );
		l3.put( "syAbStd", GladeParseUtil.grabWidget("entry35", "window1") );
		l3.put( "syMCMean", GladeParseUtil.grabWidget("entry36", "window1") );
		l3.put( "syMCStd", GladeParseUtil.grabWidget("entry37", "window1") );
		l3.put( "syDMin", GladeParseUtil.grabWidget("entry38", "window1") );
		l3.put( "syDMax", GladeParseUtil.grabWidget("entry39", "window1") );
		l3.put( "sySRMean", GladeParseUtil.grabWidget("entry46", "window1") );
		l3.put( "sySRStd", GladeParseUtil.grabWidget("entry47", "window1") );
		l3.put( "sySFDType", GladeParseUtil.grabWidget("entry44", "window1") );	
		l3.put( "sySFD", GladeParseUtil.grabWidget("entry50", "window1") );
		l3.put( "sySFDFTMean", GladeParseUtil.grabWidget("entry51", "window1") );
		l3.put( "sySFDFTStd", GladeParseUtil.grabWidget("entry52", "window1") );
		l3.put( "sySFDDTMean", GladeParseUtil.grabWidget("entry53", "window1") );
		l3.put( "sySFDDTStd", GladeParseUtil.grabWidget("entry55", "window1") );
		l3.put( "syLLType", GladeParseUtil.grabWidget("entry54", "window1") );
		l3.put( "syLLLearning", GladeParseUtil.grabWidget("entry66", "window1") );
		l3.put( "syLLFTMean", GladeParseUtil.grabWidget("entry67", "window1") );
		l3.put( "syLLFTStd", GladeParseUtil.grabWidget("entry68", "window1") );
		l3.put( "syLLDTMean", GladeParseUtil.grabWidget("entry69", "window1") );
		l3.put( "syLLDTStd", GladeParseUtil.grabWidget("entry70", "window1") );
		l3.put( "syLLNebWinMean", GladeParseUtil.grabWidget("entry71", "window1") );
		l3.put( "syLLNebWinStd", GladeParseUtil.grabWidget("entry72", "window1") );
		l3.put( "syLLPosWinMean", GladeParseUtil.grabWidget("entry73", "window1") );
		l3.put( "syLLPosWinStd", GladeParseUtil.grabWidget("entry74", "window1") );
		l3.put( "syLLPosPeakMean", GladeParseUtil.grabWidget("entry75", "window1") );
		l3.put( "syLLPosPeakStd", GladeParseUtil.grabWidget("entry76", "window1") );
		l3.put( "syLLNegMean", GladeParseUtil.grabWidget("entry77", "window1") );
		l3.put( "syLLNegStd", GladeParseUtil.grabWidget("entry78", "window1") );
		l3.put("syAddSynapse", GladeParseUtil.grabWidget( "sSynapseAdd", "window1" ) );
		l3.put("syRemSynapse", GladeParseUtil.grabWidget( "sSynapseRem", "window1" ) );
		l3.put("syAddSFD", GladeParseUtil.grabWidget( "sSFDAdd", "window1" ) );
		l3.put("syRemSFD", GladeParseUtil.grabWidget( "sSFDRem", "window1" ) );
		l3.put("syAddLearn", GladeParseUtil.grabWidget( "sLearnAdd", "window1" ) );
		l3.put("syRemLearn", GladeParseUtil.grabWidget( "sLearnRem", "window1" ) );
				
		//////////////////////////////////////////////////////////////////////////////////////////
		////    Stimuli Tab
		//////////////////////////////////////////////////////////////////////////////////////////
		l3.put( "stimuliScroll", GladeParseUtil.grabWidget( "scrolledwindow3", "window1" ) );
		l1.put( "stInjects", new ListEntity( "Stimulus Injects", "window1" ) );
		l1.put( "stStimuli", new ListEntity( "Stimuli", "window1" ) );
		
		//////////////////////////////////////////////////////////////////////////////////////////
		////    Reports Tab
		//////////////////////////////////////////////////////////////////////////////////////////
		l3.put( "reportScroll", GladeParseUtil.grabWidget( "scrolledwindow7", "window1" ) );
		l1.put( "rReports", new ListEntity( "ReportsList", "window1" ) );
		
		//////////////////////////////////////////////////////////////////////////////////////////
		////    Morphology Tab
		//////////////////////////////////////////////////////////////////////////////////////////
		l3.put( "morphScroll", GladeParseUtil.grabWidget( "scrolledwindow8", "window1" ) );
		
		
		//////////////////////////////////////////////////////////////////////////////////////////
		////    Channels Tab
		//////////////////////////////////////////////////////////////////////////////////////////
		l3.put( "channelScroll", GladeParseUtil.grabWidget( "scrolledwindow9", "window1" ) );
		
		
		//////////////////////////////////////////////////////////////////////////////////////////
		////    Networks Tab
		//////////////////////////////////////////////////////////////////////////////////////////
		l3.put( "networkScroll", GladeParseUtil.grabWidget( "scrolledwindow10", "window1" ) );
		
		
		
	}	
	
	public Widget getW( String name ) {
		
		return l3.get( name );		
	}
	
	public ListEntity getL( String name ) {
		
		return l1.get( name );	
	}
	
	public ComboEntity getC( String name ) {
		
		return l2.get( name );
	}

	public void setL1(HashMap<String, ListEntity> l1) {
		this.l1 = l1;
	}

	public HashMap<String, ListEntity> getL1() {
		return l1;
	}

	public void setL2(HashMap<String, ComboEntity> l2) {
		this.l2 = l2;
	}

	public HashMap<String, ComboEntity> getL2() {
		return l2;
	}

	public void setL3(HashMap<String, Widget> l3) {
		this.l3 = l3;
	}

	public HashMap<String, Widget> getL3() {
		return l3;
	}
		
}
