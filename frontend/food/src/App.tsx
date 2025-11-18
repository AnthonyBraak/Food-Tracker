import { useEffect, useState } from "react";
import "./App.css";

interface Food {
  foodId: number;
  foodName: string;
  foodKcal: number;
  fat: number;
  saturatedFat: number;
  carbs: number;
  sugars: number;
  fibre: number;
  protein: number;
  salt: number;
}

function App() {
  const [foods, setFoods] = useState<Food[]>([]);
  const [loading, setLoading] = useState(true);

  // Search + Sorting + Pagination
  const [search, setSearch] = useState("");
  const [sortColumn, setSortColumn] = useState<keyof Food | null>(null);
  const [sortAsc, setSortAsc] = useState(true);

  const [page, setPage] = useState(1);
  const pageSize = 10;

  const [editingId, setEditingId] = useState<number | null>(null);
  const [editRow, setEditRow] = useState<Partial<Food>>({});

  const [showAddRow, setShowAddRow] = useState(false);
  const [newFood, setNewFood] = useState<Partial<Food>>({
    foodName: "",
    foodKcal: 0,
    fat: 0,
    saturatedFat: 0,
    carbs: 0,
    sugars: 0,
    fibre: 0,
    protein: 0,
    salt: 0,
  });

  // Load data
  useEffect(() => {
    fetch("http://localhost:8080/api/foods")
      .then((r) => r.json())
      .then((data) => {
        setFoods(data);
        setLoading(false);
      });
  }, []);

  if (loading) return <h1>Loading...</h1>;

  // Sorting
  function sortData(data: Food[]) {
    if (!sortColumn) return data;
    return [...data].sort((a, b) => {
      const valA = a[sortColumn] as any;
      const valB = b[sortColumn] as any;

      if (valA < valB) return sortAsc ? -1 : 1;
      if (valA > valB) return sortAsc ? 1 : -1;
      return 0;
    });
  }

  function handleSort(column: keyof Food) {
    if (sortColumn === column) {
      setSortAsc(!sortAsc);
    } else {
      setSortColumn(column);
      setSortAsc(true);
    }
  }

  // Editing handlers
  function startEdit(food: Food) {
    setEditingId(food.foodId);
    setEditRow({ ...food });
  }

  function cancelEdit() {
    setEditingId(null);
    setEditRow({});
  }

  function updateField(field: keyof Food, value: any) {
    setEditRow({ ...editRow, [field]: value });
  }

  function saveEdit(id: number) {
    fetch(`http://localhost:8080/api/foods/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(editRow),
    })
      .then((r) => r.json())
      .then((updated) => {
        setFoods(foods.map((f) => (f.foodId === id ? updated : f)));
        cancelEdit();
      });
  }

  // Add new food
  function handleAddNew() {
    const newId =
      foods.length > 0 ? Math.max(...foods.map((f) => f.foodId)) + 1 : 1;
    const foodToAdd = { ...newFood, foodId: newId };

    fetch("http://localhost:8080/api/foods", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(foodToAdd),
    })
      .then((r) => r.json())
      .then((created) => {
        setFoods([...foods, created]);
        setNewFood({
          foodName: "",
          foodKcal: 0,
          fat: 0,
          saturatedFat: 0,
          carbs: 0,
          sugars: 0,
          fibre: 0,
          protein: 0,
          salt: 0,
        });
        setShowAddRow(false);
      });
  }

  // Delete
  function deleteFood(id: number) {
    fetch(`http://localhost:8080/api/foods/${id}`, {
      method: "DELETE",
    }).then(() => {
      setFoods(foods.filter((f) => f.foodId !== id));
    });
  }

  // Filter + Sort + Paginate
  const filtered = foods.filter((f) =>
    f.foodName.toLowerCase().includes(search.toLowerCase())
  );
  const sorted = sortData(filtered);
  const paged = sorted.slice((page - 1) * pageSize, page * pageSize);
  const totalPages = Math.ceil(sorted.length / pageSize);

  // Fields to display (excluding ID)
  const displayFields: (keyof Food)[] = [
    "foodName",
    "foodKcal",
    "fat",
    "saturatedFat",
    "carbs",
    "sugars",
    "fibre",
    "protein",
    "salt",
  ];

  return (
    <div>
      <h1>Food List</h1>

      {/* Search */}
      <input
        type="text"
        placeholder="Search by name..."
        value={search}
        onChange={(e) => {
          setSearch(e.target.value);
          setPage(1);
        }}
      />

      {/* Add Button */}
      <button onClick={() => setShowAddRow(!showAddRow)}>
        {showAddRow ? "Cancel" : "Add Food"}
      </button>

      {/* Add New Row */}
      {showAddRow && (
        <table>
          <tbody>
            <tr>
              {displayFields.map((field) => (
                <td key={field}>
                  <label style={{ display: "block" }}>{field}</label>
                  <input
                    type="text"
                    placeholder={`Enter ${field}`}
                    value={(newFood as any)[field] || ""}
                    onChange={(e) =>
                      setNewFood({
                        ...newFood,
                        [field]: e.target.value,
                      })
                    }
                  />
                </td>
              ))}
              <td>
                <button onClick={handleAddNew}>Add</button>
              </td>
            </tr>
          </tbody>
        </table>
      )}

      {/* Table */}
      <table>
        <thead>
          <tr>
            {/* Headers for sortable columns */}
            {displayFields.map((col) => (
              <th key={col} onClick={() => handleSort(col)}>
                {col} {sortColumn === col ? (sortAsc ? "↑" : "↓") : ""}
              </th>
            ))}
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {paged.map((food) =>
            editingId === food.foodId ? (
              <tr key={food.foodId}>
                {displayFields.map((field) => (
                  <td key={field}>
                    <input
                      type="text"
                      value={(editRow as any)[field] || ""}
                      onChange={(e) =>
                        updateField(field as keyof Food, e.target.value)
                      }
                    />
                  </td>
                ))}
                <td>
                  <button onClick={() => saveEdit(food.foodId)}>Save</button>
                  <button onClick={cancelEdit}>Cancel</button>
                </td>
              </tr>
            ) : (
              <tr key={food.foodId}>
                {displayFields.map((field) => (
                  <td key={field}>{food[field]}</td>
                ))}
                <td>
                  <button onClick={() => startEdit(food)}>Edit</button>
                  <button onClick={() => deleteFood(food.foodId)}>
                    Delete
                  </button>
                </td>
              </tr>
            )
          )}
        </tbody>
      </table>

      {/* Pagination Controls */}
      <div style={{ marginTop: "10px" }}>
        <button disabled={page === 1} onClick={() => setPage(page - 1)}>
          Prev
        </button>
        <span>
          Page {page} / {totalPages}
        </span>
        <button
          disabled={page === totalPages}
          onClick={() => setPage(page + 1)}
        >
          Next
        </button>
      </div>
    </div>
  );
}

export default App;
