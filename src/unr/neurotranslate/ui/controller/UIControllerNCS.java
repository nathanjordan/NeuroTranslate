package unr.neurotranslate.ui.controller;

import java.util.ArrayList;
import unr.neurotranslate.model.Data;
import unr.neurotranslate.model.FileController;
import unr.neurotranslate.ncs.Cell;
import unr.neurotranslate.ncs.Channel;
import unr.neurotranslate.ncs.Column;
import unr.neurotranslate.ncs.ColumnShell;
import unr.neurotranslate.ncs.Compartment;
import unr.neurotranslate.ncs.Event;
import unr.neurotranslate.ncs.Layer;
import unr.neurotranslate.ncs.LayerShell;
import unr.neurotranslate.ncs.NCSData;
import unr.neurotranslate.ncs.Report;
import unr.neurotranslate.ncs.SpikeShape;
import unr.neurotranslate.ncs.Stimulus;
import unr.neurotranslate.ncs.StimulusInject;
import unr.neurotranslate.ncs.SynPSG;
import unr.neurotranslate.ncs.Synapse;

public class UIControllerNCS {
	

	private Data _data;
	
	public UIControllerNCS() {
		
		NCSData d = FileController.loadNCSFile( "samples/PrototypeNCS.in" );
		
		_data = new Data();
		
		_data.ncs = d;
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Column Shells
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getColumnShells() {
		
		ArrayList<String> list = new ArrayList<String>();
		
		for( ColumnShell c : _data.ncs.columnShellList )
			
			list.add( c.type );
		
		return list;
		
		}
	
	public ColumnShell getColumnShellByType( String type ) throws Exception {
		
		for( ColumnShell c : _data.ncs.columnShellList )
			
			if( c.type == type )
				
				return c;
		
		throw new Exception("Column Shell Not found");
		
		}
	
	public ColumnShell addColumnShell() {
		
		ColumnShell c = new ColumnShell();
		
		c.type = "ColumnShell1";
		
		_data.ncs.columnShellList.add(c);
		
		return c;
		
		}
	
	public void removeColumnShell( String type ) throws Exception {
		
		for( ColumnShell c : _data.ncs.columnShellList )
			
			if( c.type == type ) {
				
				_data.ncs.columnShellList.remove(c);
				
				return;
				
				}
		
		throw new Exception("Column Shell Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Columns
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getColumns() {
		
		ArrayList<String> list = new ArrayList<String>();
		
		for( Column c : _data.ncs.columnList )
			
			list.add( c.type );
		
		return list;
		
		}
	
	public Column getColumnByType( String type ) throws Exception {
		
		for( Column c : _data.ncs.columnList )
			
			if( c.type == type )
				
				return c;
		
		throw new Exception("Column Not found");
		
		}
	
	public Column addColumn() {
		
		Column c = new Column();
		
		c.type = "Column1";
		
		_data.ncs.columnList.add(c);
		
		return c;
		
		}
	
	public void removeColumn( String type ) throws Exception {
		
		for( Column c : _data.ncs.columnList )
			
			if( c.type == type ) {
				
				_data.ncs.columnList.remove(c);
				
				return;
				
				}
		
		throw new Exception("Column Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Layer Shells
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getLayerShells() {
	
		ArrayList<String> list = new ArrayList<String>();
		
		for( LayerShell l : _data.ncs.layerShellList )
		
		list.add( l.type );
		
		return list;
		
		}
	
	public LayerShell getLayerShellByType( String type ) throws Exception {
	
	for( LayerShell l : _data.ncs.layerShellList )
	
		if( l.type == type )
		
			return l;
	
	throw new Exception("LayerShell Not found");
	
	}
	
	public LayerShell addLayerShell() {
	
		LayerShell l = new LayerShell();
		
		l.type = "LayerShell1";
		
		_data.ncs.layerShellList.add(l);
		
		return l;
		
		}
	
	public void removeLayerShell( String type ) throws Exception {
	
		for( LayerShell l : _data.ncs.layerShellList )
		
			if( l.type == type ) {
			
				_data.ncs.layerShellList.remove(l);
		
		return;
		
		}
	
		throw new Exception("LayerShell Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Layers
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getLayers() {
	
		ArrayList<String> list = new ArrayList<String>();
		
		for( Layer l : _data.ncs.layerList )
		
		list.add( l.type );
		
		return list;
		
		}
	
	public Layer getLayerByType( String type ) throws Exception {
	
	for( Layer l : _data.ncs.layerList )
	
		if( l.type == type )
		
			return l;
	
	throw new Exception("Layer Not found");
	
	}
	
	public Layer addLayer() {
	
		Layer l = new Layer();
		
		l.type = "Layer1";
		
		_data.ncs.layerList.add(l);
		
		return l;
		
		}
	
	public void removeLayer( String type ) throws Exception {
	
		for( Layer l : _data.ncs.layerList )
		
			if( l.type == type ) {
			
				_data.ncs.layerList.remove(l);
		
		return;
		
		}
	
		throw new Exception("Layer Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Cells
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getCells() {
	
		ArrayList<String> list = new ArrayList<String>();
		
		for( Cell l : _data.ncs.cellList )
		
		list.add( l.type );
		
		return list;
		
		}
	
	public Cell getCellByType( String type ) throws Exception {
	
	for( Cell c : _data.ncs.cellList )
	
		if( c.type == type )
		
			return c;
	
	throw new Exception("Cell Not found");
	
	}
	
	public Cell addCell() {
	
		Cell c = new Cell();
		
		c.type = "Cell1";
		
		_data.ncs.cellList.add(c);
		
		return c;
		
		}
	
	public void removeCell( String type ) throws Exception {
	
		for( Cell c : _data.ncs.cellList )
		
			if( c.type == type ) {
			
				_data.ncs.cellList.remove(c);
		
		return;
		
		}
	
		throw new Exception("Cell Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Compartments
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getCompartments() {
	
		ArrayList<String> list = new ArrayList<String>();
		
		for( Compartment c : _data.ncs.compartmentList )
		
		list.add( c.type );
		
		return list;
		
		}
	
	public Compartment getCompartmentByType( String type ) throws Exception {
	
	for( Compartment c : _data.ncs.compartmentList )
	
		if( c.type == type )
		
			return c;
	
	throw new Exception("Compartment Not found");
	
	}
	
	public Compartment addCompartment() {
	
		Compartment c = new Compartment();
		
		c.type = "Compartment1";
		
		_data.ncs.compartmentList.add(c);
		
		return c;
		
		}
	
	public void removeCompartment( String type ) throws Exception {
	
		for( Compartment c : _data.ncs.compartmentList )
		
			if( c.type == type ) {
			
				_data.ncs.compartmentList.remove(c);
		
		return;
		
		}
	
		throw new Exception("Compartment Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Channels
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getChannels() {
	
		ArrayList<String> list = new ArrayList<String>();
		
		for( Channel c : _data.ncs.channelList )
		
		list.add( c.type );
		
		return list;
		
		}
	
	public Channel getChannelByType( String type ) throws Exception {
	
	for( Channel c : _data.ncs.channelList )
	
		if( c.type == type )
		
			return c;
	
	throw new Exception("Channel Not found");
	
	}
	
	public Channel addChannel() {
	
		Channel c = new Channel();
		
		c.type = "Channel1";
		
		_data.ncs.channelList.add(c);
		
		return c;
		
		}
	
	public void removeChannel( String type ) throws Exception {
	
		for( Channel c : _data.ncs.channelList )
		
			if( c.type == type ) {
			
				_data.ncs.channelList.remove(c);
		
		return;
		
		}
	
		throw new Exception("Channel Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Synapses
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getSynapses() {
	
		ArrayList<String> list = new ArrayList<String>();
		
		for( Synapse s : _data.ncs.synapseList )
		
		list.add( s.type );
		
		return list;
		
		}
	
	public Synapse getSynapseByType( String type ) throws Exception {
	
	for( Synapse s : _data.ncs.synapseList )
	
		if( s.type == type )
		
			return s;
	
	throw new Exception("Synapse Not found");
	
	}
	
	public Synapse addSynapse() {
	
		Synapse s = new Synapse();
		
		s.type = "Synapse1";
		
		_data.ncs.synapseList.add(s);
		
		return s;
		
		}
	
	public void removeSynapse( String type ) throws Exception {
	
		for( Synapse s : _data.ncs.synapseList )
		
			if( s.type == type ) {
			
				_data.ncs.synapseList.remove(s);
		
		return;
		
		}
	
		throw new Exception("Synapse Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    SpikeShapes
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getSpikeShapes() {
	
		ArrayList<String> list = new ArrayList<String>();
		
		for( SpikeShape s : _data.ncs.spikeshapeList )
		
		list.add( s.type );
		
		return list;
		
		}
	
	public SpikeShape getSpikeShapeByType( String type ) throws Exception {
	
	for( SpikeShape s : _data.ncs.spikeshapeList )
	
		if( s.type == type )
		
			return s;
	
	throw new Exception("SpikeShape Not found");
	
	}
	
	public SpikeShape addSpikeShape() {
	
		SpikeShape s = new SpikeShape();
		
		s.type = "SpikeShape1";
		
		_data.ncs.spikeshapeList.add(s);
		
		return s;
		
		}
	
	public void removeSpikeShape( String type ) throws Exception {
	
		for( SpikeShape s : _data.ncs.spikeshapeList )
		
			if( s.type == type ) {
			
				_data.ncs.spikeshapeList.remove(s);
		
		return;
		
		}
	
		throw new Exception("SpikeShape Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Stimulus
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getStimuli() {
	
		ArrayList<String> list = new ArrayList<String>();
		
		for( Stimulus s : _data.ncs.stimulusList )
		
		list.add( s.type );
		
		return list;
		
		}
	
	public Stimulus getStimulusByType( String type ) throws Exception {
	
	for( Stimulus s : _data.ncs.stimulusList )
	
		if( s.type == type )
		
			return s;
	
	throw new Exception("Stimulus Not found");
	
	}
	
	public Stimulus addStimulus() {
	
		Stimulus s = new Stimulus();
		
		s.type = "Stimulus1";
		
		_data.ncs.stimulusList.add(s);
		
		return s;
		
		}
	
	public void removeStimulus( String type ) throws Exception {
	
		for( Stimulus s : _data.ncs.stimulusList )
		
			if( s.type == type ) {
			
				_data.ncs.stimulusList.remove(s);
		
		return;
		
		}
	
		throw new Exception("Stimulus Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    StimulusInject
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getStimulusInjects() {
	
		ArrayList<String> list = new ArrayList<String>();
		
		for( StimulusInject s : _data.ncs.stimulusInjectList )
		
		list.add( s.type );
		
		return list;
		
		}
	
	public StimulusInject getStimulusInjectByType( String type ) throws Exception {
	
	for( StimulusInject s : _data.ncs.stimulusInjectList )
	
		if( s.type == type )
		
			return s;
	
	throw new Exception("StimulusInject Not found");
	
	}
	
	public StimulusInject addStimulusInject() {
	
		StimulusInject s = new StimulusInject();
		
		s.type = "StimulusInject1";
		
		_data.ncs.stimulusInjectList.add(s);
		
		return s;
		
		}
	
	public void removeStimulusInject( String type ) throws Exception {
	
		for( StimulusInject s : _data.ncs.stimulusInjectList )
		
			if( s.type == type ) {
			
				_data.ncs.stimulusInjectList.remove(s);
		
		return;
		
		}
	
		throw new Exception("StimulusInject Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Reports
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getReports() {
	
		ArrayList<String> list = new ArrayList<String>();
		
		for( Report r : _data.ncs.reportList )
		
		list.add( r.type );
		
		return list;
		
		}
	
	public Report getReportByType( String type ) throws Exception {
	
	for( Report r : _data.ncs.reportList )
	
		if( r.type == type )
		
			return r;
	
	throw new Exception("Report Not found");
	
	}
	
	public Report addReport() {
	
		Report r = new Report();
		
		r.type = "Report1";
		
		_data.ncs.reportList.add(r);
		
		return r;
		
		}
	
	public void removeReport( String type ) throws Exception {
	
		for( Report r : _data.ncs.reportList )
		
			if( r.type == type ) {
			
				_data.ncs.reportList.remove(r);
		
		return;
		
		}
	
		throw new Exception("Report Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    Events
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getEvents() {
	
		ArrayList<String> list = new ArrayList<String>();
		
		for( Event e : _data.ncs.eventList )
		
		list.add( e.type );
		
		return list;
		
		}
	
	public Event getEventByType( String type ) throws Exception {
	
	for( Event e : _data.ncs.eventList )
	
		if( e.type == type )
		
			return e;
	
	throw new Exception("Event Not found");
	
	}
	
	public Event addEvent() {
	
		Event e = new Event();
		
		e.type = "Event1";
		
		_data.ncs.eventList.add(e);
		
		return e;
		
		}
	
	public void removeEvent( String type ) throws Exception {
	
		for( Event e : _data.ncs.eventList )
		
			if( e.type == type ) {
			
				_data.ncs.eventList.remove(e);
		
		return;
		
		}
	
		throw new Exception("Event Not found");
		
		}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	////    SynPSG
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public ArrayList<String> getSynPSGs() {
	
		ArrayList<String> list = new ArrayList<String>();
		
		for( SynPSG s : _data.ncs.synpsgList )
		
		list.add( s.type );
		
		return list;
		
		}
	
	public SynPSG getSynPSGByType( String type ) throws Exception {
	
	for( SynPSG s : _data.ncs.synpsgList )
	
		if( s.type == type )
		
			return s;
	
	throw new Exception("SynPSG Not found");
	
	}
	
	public SynPSG addSynPSG() {
	
		SynPSG s = new SynPSG();
		
		s.type = "SynPSG1";
		
		_data.ncs.synpsgList.add(s);
		
		return s;
		
		}
	
	public void removeSynPSG( String type ) throws Exception {
	
		for( SynPSG s : _data.ncs.synpsgList )
		
			if( s.type == type ) {
			
				_data.ncs.synpsgList.remove(s);
		
		return;
		
		}
	
		throw new Exception("SynPSG Not found");
		
		}
	
	}
