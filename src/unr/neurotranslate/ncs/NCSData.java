package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class NCSData {
	
	public Brain brain;

	public ArrayList<ColumnShell> columnShellList = new ArrayList<ColumnShell>();
	public ArrayList<Column> columnList = new ArrayList<Column>();

	public ArrayList<LayerShell> layerShellList = new ArrayList<LayerShell>();
	public ArrayList<Layer> layerList = new ArrayList<Layer>();

	public ArrayList<Cell> cellList = new ArrayList<Cell>();
	public ArrayList<Compartment> compartmentList = new ArrayList<Compartment>();

	public ArrayList<Channel> channelList = new ArrayList<Channel>();

	public ArrayList<Synapse> synapseList = new ArrayList<Synapse>();
	public ArrayList<SynFacilDepress> synFacilDepressList = new ArrayList<SynFacilDepress>();
	public ArrayList<SynLearning> synLearningList = new ArrayList<SynLearning>();
	public ArrayList<SynData> synDataList = new ArrayList<SynData>();
	public ArrayList<SynAugmentation> synAugList = new ArrayList<SynAugmentation>();

	public ArrayList<SpikeShape> spikeshapeList = new ArrayList<SpikeShape>();

	public ArrayList<Stimulus> stimulusList = new ArrayList<Stimulus>();
	public ArrayList<StimulusInject> stimulusInjectList = new ArrayList<StimulusInject>();
	public ArrayList<SynPSG> synpsgList = new ArrayList<SynPSG>();

	public ArrayList<Report> reportList = new ArrayList<Report>();

	public ArrayList<Event> eventList = new ArrayList<Event>();
	
	}
