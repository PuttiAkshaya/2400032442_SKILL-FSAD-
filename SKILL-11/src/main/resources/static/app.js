const { useState, useEffect } = React;

// -------- Local Users (simulating local JSON) --------
function LocalUserList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    try {
      const localUsersData = [
        { id: 1, name: "John Doe", email: "john@example.com", phone: "111-111-1111" },
        { id: 2, name: "Jane Smith", email: "jane@example.com", phone: "222-222-2222" },
        { id: 3, name: "Bob Johnson", email: "bob@example.com", phone: "333-333-3333" },
        { id: 4, name: "Alice Brown", email: "alice@example.com", phone: "444-444-4444" },
        { id: 5, name: "Charlie Wilson", email: "charlie@example.com", phone: "555-555-5555" }
      ];
      setUsers(localUsersData);
      setLoading(false);
    } catch (e) {
      setError("Failed to load local users");
      setLoading(false);
    }
  }, []);

  if (loading) return <div className="loading">Loading local users...</div>;
  if (error) return <div className="error">{error}</div>;

  return (
    <div className="section">
      <h2>Local Users (Local JSON)</h2>
      <div className="card-grid">
        {users.map(u => (
          <div key={u.id} className="card">
            <h3>{u.name}</h3>
            <p><b>Email:</b> {u.email}</p>
            <p><b>Phone:</b> {u.phone}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

// -------- Users from JSONPlaceholder (fetch) --------
function UserList() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/users")
      .then(res => {
        if (!res.ok) throw new Error("API error");
        return res.json();
      })
      .then(data => {
        setUsers(data);
        setLoading(false);
      })
      .catch(err => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) return <div className="loading">Loading API users...</div>;
  if (error) return <div className="error">{error}</div>;

  return (
    <div className="section">
      <h2>Users API (JSONPlaceholder + fetch)</h2>
      <div className="card-grid">
        {users.map(u => (
          <div key={u.id} className="card">
            <h3>{u.name}</h3>
            <p><b>Email:</b> {u.email}</p>
            <p><b>Phone:</b> {u.phone}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

// -------- Fake Posts from DummyJSON (fetch, Refresh, Filter) --------
function FakePostList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [userFilter, setUserFilter] = useState("");

  const loadPosts = () => {
    setLoading(true);
    setError(null);
    fetch("https://dummyjson.com/posts?limit=15")
      .then(res => {
        if (!res.ok) throw new Error("Failed to load posts");
        return res.json();
      })
      .then(data => {
        setPosts(data.posts);
        setLoading(false);
      })
      .catch(err => {
        setError(err.message);
        setLoading(false);
      });
  };

  useEffect(() => {
    loadPosts();
  }, []);

  const filtered = userFilter
    ? posts.filter(p => String(p.userId) === userFilter)
    : posts;

  return (
    <div className="section">
      <div className="header-row">
        <h2>Fake API Posts (DummyJSON)</h2>
        <button onClick={loadPosts} className="btn">Refresh</button>
      </div>

      <div className="filter-row">
        <label>Filter by userId: </label>
        <select value={userFilter} onChange={e => setUserFilter(e.target.value)}>
          <option value="">All</option>
          <option value="1">userId 1</option>
          <option value="2">userId 2</option>
          <option value="3">userId 3</option>
          <option value="4">userId 4</option>
          <option value="5">userId 5</option>
        </select>
      </div>

      {loading && <div className="loading">Loading posts...</div>}
      {error && <div className="error">{error}</div>}

      <div className="card-grid">
        {filtered.map(p => (
          <div key={p.id} className="card">
            <h3>{p.title}</h3>
            <p>{p.body}</p>
            <small>
              User: {p.userId} | Likes: {p.reactions.likes} | Dislikes: {p.reactions.dislikes}
            </small>
          </div>
        ))}
      </div>
    </div>
  );
}

// -------- Dashboard / Navigation --------
function Dashboard({ setView }) {
  return (
    <div className="section">
      <h1>News Portal Dashboard - Skill 11</h1>
      <div className="nav-row">
        <button className="btn" onClick={() => setView("local")}>Local Users</button>
        <button className="btn" onClick={() => setView("api")}>Users API</button>
        <button className="btn" onClick={() => setView("fake")}>Fake API Posts</button>
      </div>
    </div>
  );
}

// -------- Root App --------
function App() {
  const [view, setView] = useState("home");

  return (
    <div className="container">
      <Dashboard setView={setView} />
      {view === "local" && <LocalUserList />}
      {view === "api" && <UserList />}
      {view === "fake" && <FakePostList />}
    </div>
  );
}

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<App />);
