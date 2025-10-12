import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-order-detail',
  imports: [],
  templateUrl: './order-detail.html',
  styleUrl: './order-detail.css'
})
export class OrderDetail implements OnInit {
  constructor(
    private route: ActivatedRoute,
  ){
    
  }
  ngOnInit(): void {
    const orderId = this.route.snapshot.paramMap.get('id');
    console.log('Order ID:', orderId);
  }

}
