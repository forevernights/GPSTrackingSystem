
package rp.sg.GPSTrackingSystemCustomClass
{
	import flash.events.MouseEvent;
	
	import spark.components.Button;Button;
	import spark.components.Panel;
	
	[SkinState("collapsed")]
	/**
	 * The CollapsiblePanel class adds support for collapsing (minimizing) to the Spark Panel.
	 */
	public class CollapsiblePanel extends Panel
	{
		[SkinPart(required="false")]
		/**
		 *  The skin part that defines the appearance of the 
		 *  button responsible for collapsing/uncollapsing the panel.
		 */
		public var collapseButton:Button;
		
		/**
		 * Flag indicating whether this panel is collapsed (minimized) or not.
		 */ 
		public function get collapsed():Boolean
		{
			return _collapsed;
		}
		/**
		 * @private
		 */
		public function set collapsed(value:Boolean):void
		{
			_collapsed = value;
			invalidateSkinState();
		}
		
		/**
		 * @private
		 * Toggle collapsed state on collapseButton click event.
		 */
		protected function collapseButtonClickHandler(event:MouseEvent):void
		{
			collapsed = !collapsed;
		}
		
		/**
		 * @private
		 * storage variable for <code>collapsed</code>property.
		 * Add collapeButton click listener.
		 */
		protected var _collapsed:Boolean = true;
		
		override protected function partAdded(partName:String, instance:Object) : void
		{
			super.partAdded(partName, instance);
			
			if (instance == collapseButton)
			{
				Button(instance).addEventListener(MouseEvent.CLICK, collapseButtonClickHandler);
			}
		}
		
		/**
	     * @private
	     * Remove collapeButton click listener.
	     */
		override protected function partRemoved(partName:String, instance:Object) : void
		{
			if (instance == collapseButton)
			{
				Button(instance).removeEventListener(MouseEvent.CLICK, collapseButtonClickHandler);
			}
			super.partRemoved(partName, instance);
		}
		
		/**
	     *  @private
	     */
		override protected function getCurrentSkinState():String
		{
			return collapsed ? "collapsed" : super.getCurrentSkinState();
		}
	}
}