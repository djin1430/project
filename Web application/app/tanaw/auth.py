import functools
from re import template

from flask import (
    Blueprint, flash, g, redirect, render_template, request, session, url_for
)
from werkzeug.security import check_password_hash, generate_password_hash

from tanaw.db import get_db

bp = Blueprint('', __name__, url_prefix='/')


@bp.route('/register', methods=['POST'])
def register():
    if request.method == 'POST':
        username = request.form['username']
        confirm_password = request.form['confirm_password']
        password = request.form['password']
        parent_name = request.form['parent_name']
        child_name = request.form['child_name']
        contact_number = request.form['contact_number']

        db = get_db()
        error = None

        if not username:
            error = 'Username is required.'
        elif not password:
            error = 'Password is required.'

        if error is None:
            try:
                db.execute(
                    "INSERT INTO user (username, password) VALUES (?, ?)",
                    (username, generate_password_hash(password)),
                )
                db.commit()
            except db.IntegrityError:
                error = f"User {username} is already registered."
            else:
                return redirect(url_for("index"))

        return(error)


@bp.route('/register_api', methods=['POST'])
def register_api():
    if request.method == 'POST':
        data = request.get_json()

        db = get_db()
        error = None

        if not data['username']:
            error = 'Username is required.'
        elif not data['password']:
            error = 'Password is required.'

        if error is None:
            try:
                db.execute(
                    "INSERT INTO user (username, password) VALUES (?, ?)",
                    (data['username'], generate_password_hash(data['password'])),
                )
                db.commit()
            except db.IntegrityError:
                error = f"User {data['username']} is already registered."
            else:
                return redirect(url_for("index"))

        return(error)

    
@bp.route('', methods=['GET'])
def index():
    return render_template('index.html')


@bp.route('/dashboard', methods=['GET'])
def dashboard():
    return render_template('dashboard.html')
    
@bp.route('/accounts', methods=['GET'])
def accounts():
    return render_template('accounts.html')

# @bp.route('/get_accounts_api', methods=['GET'])
# def get_accounts_api():
#     if request.method == 'GET':

#         db = get_db()
#         data = list()
#         try:
#             users = db.execute(
#                 'SELECT * FROM user '
#             ).fetchall()
#             for user in users:
#                 print(user)
#                 data.append(user)
#         except Exception as e:
#             return(e)


@bp.route('/login', methods=['POST'])
def login():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        db = get_db()
        error = None
        user = db.execute(
            'SELECT * FROM user WHERE username = ?', (username,)
        ).fetchone()

        if user is None:
            error = 'Incorrect username.'
        elif not check_password_hash(user['password'], password):
            error = 'Incorrect password.'

        if error is None:
            session.clear()
            session['user_id'] = user['id']
            return redirect(url_for('dashboard'))

        return(error)

@bp.route('/update', methods=['GET', 'POST'])
def update():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']

        db = get_db()
        db.execute(
            'UPDATE user SET password = ?'
            ' WHERE username = ?',
            (generate_password_hash(password), username)
        )
        db.commit()
        
        return redirect(url_for('index'))

    else:
        return render_template('update.html')

@bp.route('/delete', methods=['GET', 'POST'])
def delete():
    if request.method == 'POST':
        username = request.form['username']

        db = get_db()
        db.execute('DELETE FROM user WHERE username = ?', (username,))
        db.commit()

        return redirect(url_for('index'))

    else:
        return render_template('delete.html')



# @bp.before_app_request
# def load_logged_in_user():
#     user_id = session.get('user_id')

#     if user_id is None:
#         g.user = None
#     else:
#         g.user = get_db().execute(
#             'SELECT * FROM user WHERE id = ?', (user_id,)
#         ).fetchone()


# @bp.route('/logout')
# def logout():
#     session.clear()
#     return redirect(url_for('index'))


# def login_required(view):
#     @functools.wraps(view)
#     def wrapped_view(**kwargs):
#         if g.user is None:
#             return redirect(url_for('auth.login'))

#         return view(**kwargs)

    # return wrapped_view