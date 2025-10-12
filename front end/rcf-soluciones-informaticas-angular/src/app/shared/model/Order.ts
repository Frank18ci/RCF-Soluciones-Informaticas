import OrderStatus from "./OrderStatus";

export default interface Order {
    id: number;
    userId: number;
    currencyCode: string;
    orderStatus: OrderStatus;
    paymentMethodCode: string;
    subtotalCents: number;
    taxTotalCents: number;
    totalCents: number;
    discountCents: number;
    notes: string;
}


