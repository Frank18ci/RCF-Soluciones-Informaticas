export default interface Category {
    id: number;
    parent: Category | null;
    name: string;
    slug: string;
}