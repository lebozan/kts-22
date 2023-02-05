import { Component, OnInit } from '@angular/core';
import Map from 'ol/Map';
import View from 'ol/View';
import VectorLayer from 'ol/layer/Vector';
import Style from 'ol/style/Style';
import Icon from 'ol/style/Icon';
import OSM from 'ol/source/OSM';
import * as olProj from 'ol/proj';
import TileLayer from 'ol/layer/Tile';
import {fromLonLat, transform} from "ol/proj";
import {Feature} from "ol";
import {Point} from "ol/geom";
import VectorSource from "ol/source/Vector";
import {Layer} from "ol/layer";

@Component({
  selector: 'app-unregistered-page',
  templateUrl: './unregistered-page.component.html',
  styleUrls: ['./unregistered-page.component.scss']
})
export class UnregisteredPageComponent implements OnInit {
  private map: Map | undefined;


  constructor() {
  }

  ngOnInit(): void {

    this.map = new Map({
      target: 'hotel_map',
      layers: [
        new TileLayer({
          source: new OSM()
        })
      ],
      view: new View({
        center: olProj.fromLonLat([19.818, 45.251]),
        zoom: 14
      })
    });
    this.map.addEventListener('click', (event: any) => {

      var markers = new VectorLayer({
        source: new VectorSource(),
        style: new Style({
          image: new Icon({
            anchor: [0.5, 1],
            src: 'D:/Users/HpZbook15/Desktop/kts-22/kts-uber-front/src/assets/icons8-place-marker-48.png'
          })
        })
      });


      var marker = new Feature(new Point(fromLonLat(transform(event.coordinate, 'EPSG:3857', 'EPSG:4326'))));
      // @ts-ignore
      markers.getSource().addFeature(marker);


      this.map?.addLayer(markers);
    });

  }



}
