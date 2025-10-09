import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { MaterialModule } from '../../../shared/modules/material/material-module';

@Component({
  selector: 'app-panel-admin',
  imports: [MatIconModule, RouterLink, RouterLinkActive, MaterialModule],
  templateUrl: './panel-admin.html',
  styleUrl: './panel-admin.css'
})
export class PanelAdmin {

}
