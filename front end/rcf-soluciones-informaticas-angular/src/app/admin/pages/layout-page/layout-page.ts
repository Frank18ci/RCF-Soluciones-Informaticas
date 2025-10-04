import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { PanelAdmin } from '../../components/panel-admin/panel-admin';

@Component({
  selector: 'app-layout-page',
  imports: [RouterOutlet, PanelAdmin],
  templateUrl: './layout-page.html',
  styleUrl: './layout-page.css'
})
export class LayoutPage {

}
