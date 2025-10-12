import Product from "./model/Product";

export default interface ProductAttributeValue {
  id: number;
  value: string;
  product: Product;
}