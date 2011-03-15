package rp.sg.GPSTrackingSystemASEntities
	
{
	import com.google.maps.LatLng;
	
	import mx.collections.ArrayList;

	public class Vehicle
	{
		private var currentLoc:LatLng;		
		private var description:String;
		private var id:Number;
		private var dropList:ArrayList;
		public function Vehicle(id:Number,currentLoc:LatLng,description:String,dropList:ArrayList){
			this.currentLoc = currentLoc;			
			this.description = description;
			this.id = id;
			this.dropList = dropList;
		}
		public function getDropPointList():ArrayList{
			return this.dropList;
		}
		public function trackCurrentLoc(currentLocation:LatLng):void{
			this.currentLoc = currentLocation;
		}
		public function getCurrentLoc():LatLng{
			return this.currentLoc;
		}		
		public function setDesc(newDescription:String):void{
			this.description = newDescription;
		}
		public function getDesc():String{
			return this.description;
		}
		public function setVehicleId(vehicleId:Number):void{
			this.id = vehicleId;
		}
		public function getVehicleId():Number{
			return this.id;
		}
	}
}