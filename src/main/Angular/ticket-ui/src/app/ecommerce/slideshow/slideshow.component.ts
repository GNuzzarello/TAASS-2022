import { Component, OnInit, ViewChild} from '@angular/core';
import {NgbCarousel, NgbSlideEvent, NgbSlideEventSource} from '@ng-bootstrap/ng-bootstrap';
import {EcommerceService} from '../services/EcommerceService';


@Component({
  selector: 'app-slideshow',
  templateUrl: './slideshow.component.html',
  styleUrls: ['./slideshow.component.css']
})
export class SlideshowComponent implements OnInit {


  constructor(private ecommerceService: EcommerceService) { }
    ssimg: [string, number][] = [['../../../assets/TOP5_1.png', 1], ['../../../assets/TOP2_3.png', 2], ['../../../assets/TOP3_2.png', 3]];
    cat = ['Sport', 'Cinema', 'Concert'];
    paused = false;
    unpauseOnArrow = false;
    pauseOnIndicator = false;
    pauseOnHover = true;
    pauseOnFocus = true;
    categorySelected: boolean = Object.create(null);
    slidescio: boolean;

    @ViewChild('carousel', {static : true}) carousel: NgbCarousel;
    togglePaused() {
        if (this.paused) {
            this.carousel.cycle();
        } else {
            this.carousel.pause();
        }
        this.paused = !this.paused;
    }
    onSlide(slideEvent: NgbSlideEvent) {
        if (this.unpauseOnArrow && slideEvent.paused &&
            (slideEvent.source === NgbSlideEventSource.ARROW_LEFT || slideEvent.source === NgbSlideEventSource.ARROW_RIGHT)) {
            this.togglePaused();
        }
        if (this.pauseOnIndicator && !slideEvent.paused && slideEvent.source === NgbSlideEventSource.INDICATOR) {
            this.togglePaused();
        }
    }
    ngOnInit() {
        this.receiveValuefromService();
    }
     categorySet() {
        this.categorySelected = true;
    }

    private receiveValuefromService() {
        this.ecommerceService.valueSource.subscribe(value => {
            this.slidescio = value;
        });
    }
}
