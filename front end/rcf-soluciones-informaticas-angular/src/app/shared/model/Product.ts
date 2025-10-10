import Category from "./Category";
import Discount from "./Discount";

export default interface Product {
  id: number;
  sku: string;
  name: string;
  shortDescription: string;
  imgUrl: string;
  description: string;
  basePriceCents: number;       
  purchasePriceCents: number;
  salePriceCents: number;
  taxRate: number;
  stock: number;
  category: Category;
  discount: Discount | null;
  active: boolean;
}