import Order from './Order';

export interface OrderItem {
    id: number;
    order: Order;
    serviceId: number;
    productId: number;
    qty: number;
    unitPriceCents: number;
    taxRate: number; // Use number for decimal values in TS
    totalLineCents: number;
    discountLineCents: number;
}