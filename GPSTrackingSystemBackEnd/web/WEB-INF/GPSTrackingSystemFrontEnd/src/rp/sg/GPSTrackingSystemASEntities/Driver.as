package rp.sg.GPSTrackingSystemASEntities
{
	public class Driver
	{
		private var id:Number;
		private var name:String;
		public function Driver(id:Number,name:String)
		{
			this.id = id;
			this.name = name;
		}
		public function getDriverId():Number{
			return this.id;
		}
		public function setDriverId(newId:Number):void{
			this.id = newId;
		}
		public function getDriverName():String{
			return this.name;
		}
		public function setDriverName(newName:String):void{
			this.name = newName;
		}
	}
}