export default interface OrderItemRequest {
    orderId: number;
    serviceId: number;
    productId: number;
    qty: number;
    unitPriceCents: number;
    taxRate: number;
    totalLineCents: number;
    discountLineCents: number;
}