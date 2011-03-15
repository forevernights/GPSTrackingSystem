package rp.sg.GPSTrackingSystemCustomClass
{
	import flash.text.TextField;
	
	import mx.containers.TabNavigator;
	import mx.containers.VBox;
	import mx.controls.TextArea;
	import mx.core.UIComponent;

	public class InfoWindowTabbedComponent extends UIComponent
	{
		private var infoTab:VBox;
		private var basisMgmTab:VBox;
		private var detailsMgmTab:VBox;
		private var tabNavigator:TabNavigator;
		public function InfoWindowTabbedComponent()
		{
			//Add body of tabs
			tabNavigator = new TabNavigator();
			tabNavigator.width = 270;
			tabNavigator.height = 150;		
			addChild(tabNavigator);			
			cacheAsBitmap = true;
		}
		public function makeInfoTab(text:String):void{			
			this.infoTab = createTab("Vehicle Information",text);
			this.tabNavigator.addChild(infoTab);
		}
		public function makeBasisMgmTab(text:String):void{
			this.basisMgmTab = createTab("Basic Management",text);
			this.tabNavigator.addChild(basisMgmTab);
		}
		public function makeDetailsMgmTab(text:String):void{
			this.detailsMgmTab = createTab("Details Management",text);
			this.tabNavigator.addChild(detailsMgmTab);
		}
		private function createTab(label:String, text:String):VBox {
			var tab:VBox = new VBox();
			tab.label = label;
			var inside:TextArea = new TextArea();
			
			inside.editable = false;
			inside.selectable = false;
			inside.width = 270;
			inside.height = 100;
			inside.htmlText = text;
			inside.setStyle("borderStyle", "none");
			tab.addChild(inside);
			return tab;
		}
	}
}