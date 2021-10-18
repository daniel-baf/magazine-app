import { Component, OnInit } from '@angular/core';
import { MagazinePost } from 'src/app/modules/MagazineMessage.module';
import { MagazineService } from 'src/app/services/Magazine/Magazine.service';

@Component({
  selector: 'app-read-post',
  templateUrl: './read-post.component.html',
  styleUrls: ['./read-post.component.css'],
})
export class ReadPostComponent implements OnInit {
  public _post: MagazinePost;

  constructor(private _magService: MagazineService) {}

  ngOnInit(): void {
    this.getBasicData();
  }

  private getBasicData() {
    
  }
}
