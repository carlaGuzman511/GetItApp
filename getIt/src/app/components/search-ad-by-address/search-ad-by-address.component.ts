import { Component, OnInit } from "@angular/core";
import * as moment from "moment";
import { ApiService } from "src/app/services/api.service";
import { Ad } from "../../models/ad.model";

@Component({
  selector: "app-search-ad-by-address",
  styleUrls: ["./search-ad-by-address.component.scss"],
  templateUrl: "./search-ad-by-address.component.html",
})
export class SearchAdByAddressComponent implements OnInit {
  public ads: Ad[] = [];
  public txtToSearch: String = "";
  constructor(private apiService: ApiService) {}

  public ngOnInit() {
    this.loadAds();
  }

  public async loadAds() {
    await this.apiService.getAll<Ad[]>("publications").subscribe((response) => {
      this.ads = response;
      for (let i in this.ads) {
        this.ads[i].createdAt = moment(this.ads[i].createdAt).format('LLL');       
      }
    });
  }

  public search(event) {
    this.txtToSearch = event.detail.value;
  }
}
